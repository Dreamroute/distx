package com.github.dreamroute.distx.starter.anno;

import com.github.dreamroute.distx.starter.rocketmq.DistxProperties;
import com.github.dreamroute.distx.starter.service.TxMessageCommitService;
import com.github.dreamroute.distx.starter.service.impl.TxMessageCommitServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;

/**
 * 生产者
 *
 * @author w.dehi
 */
@MapperScan(DistxProperties.MAPPER_CONSUMER_LOCATIONS)
public class ConsumerConfig {

    @Bean
    public TxMessageCommitService txMessageCommitService() {
        return new TxMessageCommitServiceImpl();
    }

}
