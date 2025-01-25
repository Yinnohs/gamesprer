package com.yinnohs.gamesprer;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest
public class GamesprerApplicationTests {
    final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    @BeforeAll
    public void setUp() {
        mongoDBContainer.start();
        String connectioString = mongoDBContainer.getConnectionString();
        System.setProperty("spring.data.mongodb.uri", connectioString);
    }

    @Test
    public void contextLoads() {
    }



}
