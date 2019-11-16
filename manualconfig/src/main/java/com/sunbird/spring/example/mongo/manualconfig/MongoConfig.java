package com.sunbird.spring.example.mongo.manualconfig;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClientFactory;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableConfigurationProperties(MongoProperties.class)
public class MongoConfig {
    @Autowired
    private MongoProperties mongoProperties;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory(mongoProperties));
    }

    @Bean
    public MongoDbFactory mongoDbFactory(MongoProperties mongo) throws Exception {

        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        // 连接池配置
        builder.maxWaitTime(1000 * 60 * 1).socketTimeout(30 * 1000).connectTimeout(10 * 1000).connectionsPerHost(60)
                .minConnectionsPerHost(60).socketKeepAlive(true);

        // 设置鉴权信息
        MongoCredential credential = null;
        if (!StringUtils.isEmpty(mongo.getUsername())) {
            credential = MongoCredential.createCredential(mongo.getUsername(), mongo.getDatabase(),
                    mongo.getPassword());
        }

        MongoClientOptions mongoOptions = builder.build();

        List<ServerAddress> addrs = Arrays.asList(new ServerAddress(mongo.getHost(), mongo.getPort()));
        MongoClient mongoClient = null;
        if (credential != null) {
            mongoClient = new MongoClient(addrs, credential, mongoOptions);
        } else {
            mongoClient = new MongoClient(addrs, mongoOptions);
        }
        return new SimpleMongoDbFactory(mongoClient, mongo.getDatabase());
    }
}
