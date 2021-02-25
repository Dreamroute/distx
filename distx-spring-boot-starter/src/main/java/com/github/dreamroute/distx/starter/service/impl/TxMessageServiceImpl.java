package com.github.dreamroute.distx.starter.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.dreamroute.distx.starter.domain.TxMessage;
import com.github.dreamroute.distx.starter.domain.TxMessageDel;
import com.github.dreamroute.distx.starter.mapper.producer.TxMessageMapper;
import com.github.dreamroute.distx.starter.rocketmq.DistxProperties;
import com.github.dreamroute.distx.starter.rocketmq.TxBody;
import com.github.dreamroute.distx.starter.service.TxMessageDelService;
import com.github.dreamroute.distx.starter.service.TxMessageService;
import com.vip.vjtools.vjkit.mapper.BeanMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @author w.dehai
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TxMessageServiceImpl implements TxMessageService {

    @Autowired
    private TxMessageMapper txMessageMapper;
    @Autowired
    private TxMessageDelService txMessageDelService;
    @Autowired
    private RocketMQTemplate rocketMqTemplate;

    @Value(DistxProperties.PAGE_SIZE_VALUE)
    private int pageSize;
    @Value(DistxProperties.IS_TEST_VALUE)
    private boolean isTest;
    @Value(DistxProperties.FAILD_TIMES_VALUE)
    private int faildTimes;


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
        return txMessageMapper.selectTxMessageByPage(start, pageSize, faildTimes);
    }

    @Override
    public void syncTxMessage2RocketMq() {
        List<TxMessage> txMsgList = this.selectTxMessageByPage(pageSize, 1);
        processMsgList(txMsgList);
    }

    @Override
    @Transactional
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

                Message<TxBody> msg = MessageBuilder.withPayload(txBody).build();
                try {
                    rocketMqTemplate.sendMessageInTransaction(txMessage.getTopic() + ":" + txMessage.getTag(), msg, txMessage.getId());
                } catch (Exception e) {
                    log.info("同步DB -> MQ失败, msg: " + toJSONString(txMessage));
                    log.info("", e);
                    // 增加失败次数
                    this.addFaildTimes(msg.getPayload().getId());
                }
            }
        }
    }

}
