package com.dummy.profiles;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileInit {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @PostConstruct
    public void init() {
        dataSourceConfig.setup();
    }
}
