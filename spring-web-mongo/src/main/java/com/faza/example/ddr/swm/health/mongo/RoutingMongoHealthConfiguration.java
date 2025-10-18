package com.faza.example.ddr.swm.health.mongo;

import com.faza.example.ddr.swm.service.DatabaseRoutingService;
import com.mongodb.client.MongoClient;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ConditionalOnEnabledHealthIndicator("mongo")
public class RoutingMongoHealthConfiguration {

    @Autowired
    @Qualifier("mongoClientMap")
    private Map<String, MongoClient> mongoClientMap;

    @Autowired
    private DatabaseRoutingService databaseRoutingService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public HealthContributor mongoHealthContributor() {
        Map<String, String> routeMap = mongoClientMap.keySet()
            .stream()
            .collect(Collectors.toMap(Function.identity(), Function.identity()));
        return CompositeHealthContributor.fromMap(routeMap, this::createMongoHealthContributor);
    }

    private HealthContributor createMongoHealthContributor(String route) {
        return new RoutingMongoHealthIndicator(route, databaseRoutingService, mongoTemplate);
    }
}
