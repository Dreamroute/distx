package com.github.dreamroute.distx.sample.consumer;

import com.github.dreamroute.distx.starter.domain.TxMessageCommit;
import com.github.dreamroute.distx.starter.rocketmq.TxBody;
import com.github.dreamroute.distx.starter.service.TxMessageCommitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.github.dreamroute.distx.sample.consumer.Tag.ORDER_CREATE;

@Slf4j
@Service
@AllArgsConstructor
@RocketMQMessageListener(topic = "distx", selectorExpression = "*", consumerGroup = ORDER_CREATE)
public class CommonConsumer implements RocketMQListener<Object> {
    @Override
    public void onMessage(Object message) {
        System.err.println(message);
    }

    //        private final TxMessageCommitService txMessageCommitService;
//
//    @Override
//    public void onMessage(TxBody body) {
//        try {
//            log.info("TxBody: {}", toJSONString(body));
//
//            // 业务就写在这里
//
//            // 消费过的记录入库，在面对极端情况时候可以手动对账、补偿
//            TxMessageCommit msg = new TxMessageCommit();
//            msg.setMessageTableId(body.getId());
//            msg.setBody(body.getBody());
////            txMessageCommitService.insert(msg);
//
//
//        } catch (Exception e) {
//            throw new RuntimeException("业务异常" + e, e);
//        }
//    }
}
