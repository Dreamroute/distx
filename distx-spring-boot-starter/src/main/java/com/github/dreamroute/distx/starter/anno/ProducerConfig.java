package com.github.dreamroute.distx.starter.anno;

import com.github.dreamroute.distx.starter.listener.SyncListener;
import com.github.dreamroute.distx.starter.listener.SyncTask;
import com.github.dreamroute.distx.starter.rocketmq.DistxProperties;
import com.github.dreamroute.distx.starter.service.TxMessageCommitService;
import com.github.dreamroute.distx.starter.service.TxMessageDelService;
import com.github.dreamroute.distx.starter.service.TxMessageService;
import com.github.dreamroute.distx.starter.service.impl.TxMessageCommitServiceImpl;
import com.github.dreamroute.distx.starter.service.impl.TxMessageDelServiceImpl;
import com.github.dreamroute.distx.starter.service.impl.TxMessageServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 生产者
 *
 * @author w.dehi
 */
@EnableConfigurationProperties(DistxProperties.class)
public class ProducerConfig {

    private DistxProperties distxProperties;

    public ProducerConfig(DistxProperties distxProperties) {
        this.distxProperties = distxProperties;
    }

    @Bean
    public TxMessageService txMessageService() {
        return new TxMessageServiceImpl();
    }

    @Bean
    public TxMessageDelService txMessageDelService() {
        return new TxMessageDelServiceImpl();
    }

    @Bean
    public TxMessageCommitService txMessageCommitService() {
        return new TxMessageCommitServiceImpl();
    }

    @Bean
    public SyncListener syncListener() {
        return new SyncListener();
    }

    @Bean
    public SyncTask syncTask() {
        return new SyncTask();
    }

}
