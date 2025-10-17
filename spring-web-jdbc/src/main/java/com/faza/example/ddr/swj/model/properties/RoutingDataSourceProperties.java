package com.faza.example.ddr.swj.model.properties;

import com.zaxxer.hikari.HikariConfig;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class RoutingDataSourceProperties {

    @NotBlank
    private String defaultTargetDataSource;

    @NotEmpty
    private Map<String, HikariConfig> targetDataSources;
}
