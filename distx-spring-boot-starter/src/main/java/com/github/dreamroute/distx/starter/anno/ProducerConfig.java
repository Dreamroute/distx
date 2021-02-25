package com.github.dreamroute.distx.starter.anno;

import com.github.dreamroute.distx.starter.listener.SyncListener;
import com.github.dreamroute.distx.starter.listener.SyncTask;
import com.github.dreamroute.distx.starter.rocketmq.DistxProperties;
import com.github.dreamroute.distx.starter.service.TxMessageDelService;
import com.github.dreamroute.distx.starter.service.TxMessageService;
import com.github.dreamroute.distx.starter.service.impl.TxMessageDelServiceImpl;
import com.github.dreamroute.distx.starter.service.impl.TxMessageServiceImpl;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

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

    @Bean
    public SyncTask syncTask() {
        return new SyncTask();
    }

    @Autowired
    private Environment env;

    @Bean
    public Object registryXmlMappers(Configuration configuration) {
        return null;
    }

}
