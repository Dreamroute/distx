package com.github.dreamroute.distx.sample;

import com.github.dreamroute.mybatis.pro.autoconfiguration.MyBatisProAutoConfiguration;
import com.github.dreamroute.sqlprinter.starter.anno.EnableSQLPrinter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableSQLPrinter
@MapperScan("com.github.dreamroute.distx.starter.mapper")
@SpringBootApplication(exclude = {MyBatisProAutoConfiguration.class}, scanBasePackages = {"com.github.dreamroute.distx.starter"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
