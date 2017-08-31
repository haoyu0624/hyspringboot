package com.hy.mongo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by haoy on 2017/7/27.
 */
@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return "octopus";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(new MongoClientURI("mongodb://octopus:octopus123@10.129.56.190:27017/octopus"));
    }
}
