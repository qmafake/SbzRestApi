package com.isorest.config;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers("/", "/v1/rest/iso/converter").authenticated();
        http.csrf().disable();
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        Map<String, String> users = getUsers();

        for (String username : users.keySet()) {
            auth.inMemoryAuthentication()
                    .withUser(username).password(users.get(username)).roles("USER");
        }
    }

    public static Map<String, String> getUsers() {

        LinkedHashMap<String, String> linked = new LinkedHashMap<String, String>();
        linked.put("isorequest", "150r3qu3s+");
        return linked;
    }
}
