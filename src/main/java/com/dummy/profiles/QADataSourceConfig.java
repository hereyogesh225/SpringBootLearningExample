package com.dummy.profiles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("qa")
public class QADataSourceConfig implements DataSourceConfig {

    @Override
    public void setup() {
        log.info("Setting up datasource for QA environment.");
    }
}