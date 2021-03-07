package com.github.dreamroute.distx.sample;

import com.github.dreamroute.distx.starter.domain.TxMessage;
import com.github.dreamroute.distx.starter.rocketmq.TxBody;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

import static com.github.dreamroute.distx.sample.consumer.Tag.ONE_WAY;
import static com.github.dreamroute.distx.sample.consumer.Tag.ORDER_CREATE;
import static com.github.dreamroute.distx.sample.consumer.Tag.WMS_DECREMENT;
import static java.util.concurrent.TimeUnit.SECONDS;

@SpringBootTest
class MQTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Value("${distx.topic}")
    private String topic;

    @Test
    void sendNoSendResultTest() {
        TxMessage message = new TxMessage();
        rocketMQTemplate.send(topic + ":" + ORDER_CREATE, MessageBuilder.withPayload("fuyun").build());
    }

    @Test
    void sentSyncTest() throws Exception {
        long i = 1;
        while (true) {
            TxBody body1 = new TxBody();
            body1.setId(i);
            body1.setBody(ORDER_CREATE + i);

            TxBody body2 = new TxBody();
            body2.setId(i);
            body2.setBody(WMS_DECREMENT + i);

            i++;
            rocketMQTemplate.syncSend(topic + ":" + ORDER_CREATE, MessageBuilder.withPayload(body1).build());
            SECONDS.sleep(2L);
            rocketMQTemplate.syncSend(topic + ":" + WMS_DECREMENT, MessageBuilder.withPayload(body2).build());
        }
    }

    @Test
    void sendOnewayTest() {
        for (int i=0; i<10; i++)
            rocketMQTemplate.sendOneWay(topic + ":" + ONE_WAY, MessageBuilder.withPayload("oneway").build());
        System.err.println("END");
    }

    @Test
    void sendDelayTest() throws Exception {
        Message<String> delay = MessageBuilder.withPayload("delay").build();
        Object o = rocketMQTemplate.sendAndReceive(topic + ":" + WMS_DECREMENT, delay, String.class, 2000, 3);
        SECONDS.sleep(Integer.MAX_VALUE);
    }

}
