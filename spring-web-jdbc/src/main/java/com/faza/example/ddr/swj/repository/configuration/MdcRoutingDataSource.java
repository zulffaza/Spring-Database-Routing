package com.faza.example.ddr.swj.repository.configuration;

import com.faza.example.ddr.swj.model.constant.MdcConstant;
import org.slf4j.MDC;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MdcRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return MDC.get(MdcConstant.DATABASE_ROUTE);
    }
}
