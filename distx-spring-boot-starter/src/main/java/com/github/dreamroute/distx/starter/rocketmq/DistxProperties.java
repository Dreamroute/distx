package com.github.dreamroute.distx.starter.rocketmq;

import com.github.dreamroute.distx.starter.exception.DistxException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

import static java.lang.Boolean.TRUE;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * RocketMQ客户端配置信息
 * 
 * @author w.dehai
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "distx")
public class DistxProperties {

    public static final String IS_TEST_VALUE = "${distx.is-test:false}";
    public static final String PAGE_SIZE_VALUE = "${distx.page-size:3}";
    public static final String CRON_VALUE = "${distx.cron:*/3 * * * * ?}";
    public static final String TOPIC_VALUE = "${distx.topic}";
    public static final String FAILD_TIMES_VALUE = "${distx.faild-times:5}";
    public static final String MAPPER_PRODUCER_LOCATIONS = "com.github.dreamroute.distx.starter.mapper.producer";
    public static final String MAPPER_CONSUMER_LOCATIONS = "com.github.dreamroute.distx.starter.mapper.consumer";

    // 是否是用于测试
    @Value(IS_TEST_VALUE)
    private Boolean isTest = Boolean.FALSE;

    // 同步消息时每次查询数据库的行数
    @Value(PAGE_SIZE_VALUE)
    private Integer pageSize = 3;

    // 同步消息的频率
    @Value(CRON_VALUE)
    private String cron = "*/3 * * * * ?";

    // Topic，全局唯一，每个应用使用一个topic，应用下的不同业务使用Tag区分
    @Value(TOPIC_VALUE)
    private String topic;

    // 失败多少次就不再进行发送消息
    @Value(FAILD_TIMES_VALUE)
    private int faildTimes = 5;

    /** Template配置 */
//    @Value("${rocketmq.name-server}")
//    private String nameServer;
//    @Value("${rocketmq.producer.group}")
//    private String producerGroup;

    @PostConstruct
    public void checkProperties() {

        String errorMsg = "";
        if (TRUE.equals(isTest)) {
            errorMsg = ", 你的isTest属性是true，如果不是用于测试，请务必使用默认值或者设置成false";
        }

        log.info("");
        log.info("~~~~~~~~~~~~~~~~RocketMQ~~~~~~~~~~~~~~~~");
        log.info("#   topic: {}", topic);
        log.info("#   isTest: {}{}", isTest, errorMsg);
        log.info("#   pageSize: {}", pageSize);
        log.info("#   cron: {}", cron);
        log.info("#   faildTimes: {}", faildTimes);
//        log.info("#   nameServer: {}", nameServer);
//        log.info("#   producerGroup: {}", producerGroup);
        log.info("~~~~~~~~~~~~~~~~RocketMQ~~~~~~~~~~~~~~~~");
        log.info("");

//        if (isEmpty(nameServer)) {
//            throw new DistxException("rocketmq.name-server不允许为空!");
//        }
//        if (isEmpty(producerGroup)) {
//            throw new DistxException("rocketmq.name-server不允许为空，并且需要全局唯一，建议使用[应用名称:端口]或者UUID方式!");
//        }
        if (isEmpty(topic)) {
            throw new DistxException("distx.topic不允许为空，建议一个应用一个topic，应用中的不同业务以tag区分!");
        }
    }

}
