package com.krish.appsec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppsecSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/_ns_/**", "/resources/**", "/static/**", 
        		"/css/**", "/js/**", "/images/**", "/fonts/**", "/img/**");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/**").permitAll();
    }
}
