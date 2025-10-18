package com.faza.example.ddr.swm.service.impl;

import com.faza.example.ddr.swm.model.constant.MdcConstant;
import com.faza.example.ddr.swm.service.DatabaseRoutingService;
import jakarta.annotation.Nullable;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class DatabaseRoutingServiceImpl implements DatabaseRoutingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseRoutingServiceImpl.class);

    @Override
    public <T> T execute(@Nullable String name, Supplier<T> supplier) {
        try {
            MDC.put(MdcConstant.DATABASE_ROUTE, name);
            LOGGER.warn("Execute with database route: {}", name);
            return supplier.get();
        } finally {
            LOGGER.warn("Done execution with database route: {}", name);
            MDC.remove(MdcConstant.DATABASE_ROUTE);
        }
    }
}
