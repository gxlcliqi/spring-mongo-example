package com.sunbird.spring.example.mongo.manualconfig;

import com.sunbird.spring.example.mongo.common.MongoService;
import com.sunbird.spring.example.mongo.common.Product;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ManualconfigApplication.class)
public class MongoServiceTest extends BaseEmbeddedMongoTest{
    @Autowired
    MongoService mongoService;

    @Test
    public void add() {
        System.out.println("test add");
        Product product = new Product();
        product.setId(2L);
        product.setName("hello");
        mongoService.add(product);
        Product product1 = mongoService.get(2L);
        assertThat(product1.getName()).isEqualTo("hello");
    }
}
