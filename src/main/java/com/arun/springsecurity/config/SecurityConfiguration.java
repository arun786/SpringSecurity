package com.arun.springsecurity.config;

import com.arun.springsecurity.Enum.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile(value = "in-memory")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserConfiguration userConfiguration;

    @Autowired
    public SecurityConfiguration(UserConfiguration userConfiguration) {
        this.userConfiguration = userConfiguration;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/dashboard").hasAnyRole(Roles.ADMIN.toString()).and().
                httpBasic().and().formLogin().and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(userConfiguration.getUserName())
                .password("{noop}"+userConfiguration.getPassword())
                .roles(Roles.ADMIN.toString());
    }
}
