package com.github.dreamroute.distx.starter.anno;

import com.github.dreamroute.distx.starter.listener.SyncListener;
import com.github.dreamroute.distx.starter.rocketmq.DistxProperties;
import com.github.dreamroute.distx.starter.service.TxMessageDelService;
import com.github.dreamroute.distx.starter.service.TxMessageService;
import com.github.dreamroute.distx.starter.service.impl.TxMessageDelServiceImpl;
import com.github.dreamroute.distx.starter.service.impl.TxMessageServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 生产者
 *
 * @author w.dehi
 */
@EnableConfigurationProperties(DistxProperties.class)
@MapperScan(DistxProperties.MAPPER_PRODUCER_LOCATIONS)
public class ProducerConfig {

    @Bean
    public TxMessageService txMessageService() {
        return new TxMessageServiceImpl();
    }

    @Bean
    public TxMessageDelService txMessageDelService() {
        return new TxMessageDelServiceImpl();
    }
    @Bean
    public SyncListener syncListener() {
        return new SyncListener();
    }

}
