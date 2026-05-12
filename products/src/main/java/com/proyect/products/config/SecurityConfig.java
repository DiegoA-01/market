/*package com.proyect.products.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exeption{

        http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/v1/products/**").hasRole("ADMIN")
        .antMatchers("/api/v1/users/**").hasRole("ADMIN")
        .antMatchers("/api/v1/sales/**").hasAnyRole("ADMIN", "CASHIER")
        .antMatchers("/api/auth/cashier/**").hasRole("CASHIER")
        .antMatchers("/api/auth/admin/**").hasRole("ADMIN")
        .antMatchers("/api/auth/login").permitAll()
        .anyRequest().authenticated();
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
    
}*/
