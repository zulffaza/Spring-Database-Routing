package com.faza.example.ddr.swm.configuration;

import com.faza.example.ddr.swm.model.properties.RoutingDataSourceProperties;
import com.faza.example.ddr.swm.repository.configuration.MdcRoutingMongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.mongo.MongoClientFactory;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.PropertiesMongoConnectionDetails;
import org.springframework.boot.autoconfigure.mongo.StandardMongoClientSettingsBuilderCustomizer;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public Map<String, MongoClient> mongoClientMap(RoutingDataSourceProperties routingDataSourceProperties,
        ObjectProvider<SslBundles> sslBundles) {
        return routingDataSourceProperties.getTargetDataSources()
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey,
                entry -> buildMongoClient(entry.getValue(), sslBundles)));
    }

    @Bean
    @Primary
    public MongoClient mongoClient(RoutingDataSourceProperties routingDataSourceProperties,
        Map<String, MongoClient> mongoClientMap) {
        return new MdcRoutingMongoClient(routingDataSourceProperties.getDefaultTargetDataSource(),
            mongoClientMap);
    }

    private MongoClient buildMongoClient(MongoProperties mongoProperties,
        ObjectProvider<SslBundles> sslBundles) {
        List<MongoClientSettingsBuilderCustomizer> mongoClientSettingsBuilderCustomizers =
            List.of(new StandardMongoClientSettingsBuilderCustomizer(new PropertiesMongoConnectionDetails(
                mongoProperties,
                sslBundles.getIfAvailable()), mongoProperties.getUuidRepresentation()));
        return new MongoClientFactory(mongoClientSettingsBuilderCustomizers).createMongoClient(
            MongoClientSettings.builder().build());
    }
}
