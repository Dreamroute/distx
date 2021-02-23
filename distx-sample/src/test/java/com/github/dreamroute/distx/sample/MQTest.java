package com.github.dreamroute.distx.sample;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@SpringBootTest
class MQTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void sendTest() {
        rocketMQTemplate.send("TopicTest", MessageBuilder.withPayload("fuyun").build());
    }

}
