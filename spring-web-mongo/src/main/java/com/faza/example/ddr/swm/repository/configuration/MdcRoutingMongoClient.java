package com.faza.example.ddr.swm.repository.configuration;

import com.faza.example.ddr.swm.model.constant.MdcConstant;
import com.mongodb.client.MongoClient;
import java.util.Map;
import org.slf4j.MDC;

public class MdcRoutingMongoClient extends RoutingMongoClient {

    public MdcRoutingMongoClient(String defaultRouteName, Map<String, MongoClient> mongoClients) {
        super(defaultRouteName, mongoClients);
    }

    @Override
    public String getRouteName() {
        return MDC.get(MdcConstant.DATABASE_ROUTE);
    }
}
