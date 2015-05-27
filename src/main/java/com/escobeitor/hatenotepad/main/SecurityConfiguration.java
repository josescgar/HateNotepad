package com.escobeitor.hatenotepad.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring security module configuration based
 * on "in-memory" credentials authentication
 * Created by escobeitor on 27/05/15.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Main method for setting url mapping security configuration
     * @param http HTTP Wrapper
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/person/insert").hasRole("ADMIN")
            .antMatchers("/audit/**").hasRole("AUDITOR")
            .antMatchers("/note/**/delete").hasRole("ADMIN")
            .antMatchers("/**").hasRole("USER")
                .anyRequest().authenticated();

        http.httpBasic();
        http.csrf().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password("admin").roles("USER", "AUDITOR", "ADMIN").and()
            .withUser("escobeitor").password("manolo").roles("USER");
    }
}
