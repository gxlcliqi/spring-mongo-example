package com.sunbird.spring.example.mongo.autoconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.sunbird.spring.example.mongo"})
public class AutoconfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoconfigApplication.class, args);
    }

}
