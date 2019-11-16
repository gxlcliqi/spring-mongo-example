package com.sunbird.spring.example.mongo.manualconfig;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication(exclude={
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class,
        EmbeddedMongoAutoConfiguration.class
})
@ComponentScan(basePackages={"com.sunbird.spring.example.mongo"})
public class ManualconfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManualconfigApplication.class, args);
    }
}
