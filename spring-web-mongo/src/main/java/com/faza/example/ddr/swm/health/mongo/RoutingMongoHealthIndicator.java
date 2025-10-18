package com.faza.example.ddr.swm.health.mongo;

import com.faza.example.ddr.swm.service.DatabaseRoutingService;
import org.springframework.boot.actuate.data.mongo.MongoHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.data.mongodb.core.MongoTemplate;

public class RoutingMongoHealthIndicator extends MongoHealthIndicator {

    private final String route;
    private final DatabaseRoutingService databaseRoutingService;

    public RoutingMongoHealthIndicator(String route,
        DatabaseRoutingService databaseRoutingService,
        MongoTemplate mongoTemplate) {
        super(mongoTemplate);
        this.route = route;
        this.databaseRoutingService = databaseRoutingService;
    }

    @Override
    public Health getHealth(boolean includeDetails) {
        return databaseRoutingService.execute(route, () -> super.getHealth(includeDetails));
    }
}
