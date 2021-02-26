package com.github.dreamroute.distx.sample;

import com.github.dreamroute.distx.sample.consumer.Abc;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@SpringBootTest
class MQTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private Abc abc;

    @Test
    void sendTest() {
        System.err.println(abc);
        rocketMQTemplate.send("TopicTest", MessageBuilder.withPayload("fuyun").build());
    }

}
