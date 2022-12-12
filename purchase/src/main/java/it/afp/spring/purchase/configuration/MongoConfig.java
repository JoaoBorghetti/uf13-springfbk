package it.afp.spring.purchase.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Value("${MONGO_DB:test}") private String DB_NAME;
    @Value("${MONGO_HOST:localhost}") private String DB_HOST;
    @Value("${MONGO_PORT:27017}") private int DB_PORT;

    @Override
    protected String getDatabaseName(){return DB_NAME;}

    @Override
    public MongoClient mongoClient(){
        String connString = DB_HOST+":"+DB_PORT+"/"+DB_NAME;
        ConnectionString connectionString = new ConnectionString("mongodb://"+connString);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }
}
