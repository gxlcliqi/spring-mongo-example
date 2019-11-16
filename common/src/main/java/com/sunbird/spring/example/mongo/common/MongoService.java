package com.sunbird.spring.example.mongo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void delete(Long id) {
        Product product = this.get(id);
        if(product!=null) {
            mongoTemplate.remove(product);
        }
    }

    public void add(Product product) {
        mongoTemplate.insert(product);
    }

    public Product get(Long id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Product.class);
    }
}
