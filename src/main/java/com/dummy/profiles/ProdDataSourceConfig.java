package com.dummy.profiles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("prod")
public class ProdDataSourceConfig implements DataSourceConfig{

    @Override
    public void setup() {
        log.info("Setting up datasource for Prod environment.");
    }
}