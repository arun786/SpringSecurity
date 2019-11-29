package com.arun.springsecurity.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring")
@Getter
@Setter
@Profile(value = "in-memory")
public class UserConfiguration {
    private String userName;
    private String password;
}
