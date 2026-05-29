package com.yrog.famiplan.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "app.admin")
@Getter
@Setter
public class AppAdminConfig {

    private String username;
    private String password;

}
