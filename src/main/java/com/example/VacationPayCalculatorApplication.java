package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties
public class VacationPayCalculatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(VacationPayCalculatorApplication.class,args);
    }
}
