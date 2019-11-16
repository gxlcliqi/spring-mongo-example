package com.sunbird.spring.example.mongo.manualconfig;

import com.sunbird.spring.example.mongo.common.MongoService;
import com.sunbird.spring.example.mongo.common.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class MongoServiceTest extends BaseEmbeddedMongoTest {
    @Autowired
    MongoService mongoService;

    @Test
    void add() {
        Product product = new Product();
        product.setId(2L);
        product.setName("hello");
        mongoService.add(product);
        Product product1 = mongoService.get(2L);
        assertThat(product.getName()).isEqualTo("hello");
    }
}
