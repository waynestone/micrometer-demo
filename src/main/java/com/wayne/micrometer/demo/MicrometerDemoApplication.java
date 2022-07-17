package com.wayne.micrometer.demo;

import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class MicrometerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrometerDemoApplication.class, args);


    }

//    @Bean
//    public PrometheusMeterRegistry init() {
//        return new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
//    }

}
