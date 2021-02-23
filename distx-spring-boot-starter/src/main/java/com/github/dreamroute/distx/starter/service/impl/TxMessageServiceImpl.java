package com.github.dreamroute.distx.starter.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.dreamroute.distx.starter.domain.TxMessage;
import com.github.dreamroute.distx.starter.domain.TxMessageDel;
import com.github.dreamroute.distx.starter.exception.SdkException;
import com.github.dreamroute.distx.starter.mapper.TxMessageMapper;
import com.github.dreamroute.distx.starter.rocketmq.TxBody;
import com.github.dreamroute.distx.starter.service.TxMessageDelService;
import com.github.dreamroute.distx.starter.service.TxMessageService;
import com.vip.vjtools.vjkit.mapper.BeanMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * @author w.dehai
 *
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TxMessageServiceImpl implements TxMessageService {

    @Autowired
    private TxMessageMapper txMessageMapper;
    @Autowired
    private TxMessageDelService txMessageDelService;
    @Autowired
    private RocketMQTemplate rocketMqTemplate;

    @Value("${rocketmq.pageSize:1}")
    private int pageSize;
    @Value("${rocketmq.isTest:false}")
    private boolean isTest;
    public int insert(TxMessage message) {
        if (message.getCreateTime() == null) {
            message.setCreateTime(new Timestamp(System.currentTimeMillis()));
        }
        return txMessageMapper.insert(message);
    }

    @Override
    public void deleteById(Long id) {
        TxMessage msg = txMessageMapper.selectByPrimaryKey(id);
        TxMessageDel del = BeanMapper.map(msg, TxMessageDel.class);
        del.setCreateTime(new Timestamp(System.currentTimeMillis()));
        // fix issue#1, 让del表自增
        del.setId(null);
        txMessageDelService.insert(del);
        txMessageMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TxMessage> selectTxMessageByPage(int pageSize, int pageNo) {
        int start = (pageNo - 1) * pageSize;
        return txMessageMapper.selectTxMessageByPage(start, pageSize);
    }

    @Override
    public void syncTxMessage2RocketMq() {
        List<TxMessage> txMsgList = this.selectTxMessageByPage(pageSize, 1);
        processMsgList(txMsgList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addFaildTimes(long id) {
        txMessageMapper.addFaildTimes(id);
    }

    public void processMsgList(List<TxMessage> txMsgList) {
        if (txMsgList != null && !txMsgList.isEmpty()) {
            log.info("查询消息表：{}", JSON.toJSON(txMsgList));
        }
        if (txMsgList != null && !txMsgList.isEmpty()) {
            for (TxMessage txMessage : txMsgList) {
                TxBody txBody = new TxBody();
                txBody.setId(txMessage.getId());
                txBody.setBody(txMessage.getBody());

                TransactionSendResult result = null;
                Message<TxBody> msg = MessageBuilder.withPayload(txBody).build();
                try {
                    int a = 1/0;
                    result = rocketMqTemplate.sendMessageInTransaction(txMessage.getTopic() + ":" + txMessage.getTag(), msg, txMessage.getId());
                } catch (Exception e) {
                    log.info("同步DB -> MQ失败!" + e, e);
                    // 增加失败次数
                    this.addFaildTimes(msg.getPayload().getId());
                }
                throw new SdkException("同步DB -> MQ结果: " + JSON.toJSONString(result));
            }
        }
    }

}
