package com.sunbird.spring.example.mongo.common;

import com.sunbird.spring.example.mongo.common.MongoService;
import com.sunbird.spring.example.mongo.common.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MongoRunner implements ApplicationRunner {
    @Autowired
    MongoService mongoService;

    public void run(ApplicationArguments args) throws Exception {
        mongoService.delete(1L);

        Product product = new Product();
        product.setId(1L);
        product.setName("test123");
        mongoService.add(product);

        Product result = mongoService.get(1L);
        System.out.println(result.getName());
    }
}
