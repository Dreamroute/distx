package com.github.dreamroute.distx.sample;

import com.github.dreamroute.distx.starter.anno.EnableConsumer;
import com.github.dreamroute.distx.starter.anno.EnableProducer;
import com.github.dreamroute.sqlprinter.starter.anno.EnableSQLPrinter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableProducer
@EnableConsumer
@EnableScheduling
@EnableSQLPrinter
@SpringBootApplication(scanBasePackages = {"com.github.dreamroute.distx.sample.consumer"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
