package com.github.dreamroute.distx.sample.consumer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public Abc abc() {
        return new Abc();
    }
}
