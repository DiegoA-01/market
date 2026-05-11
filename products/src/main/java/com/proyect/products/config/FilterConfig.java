package com.proyect.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import com.proyect.products.filter.JwtValidationFilter;

@Configuration
public class FilterConfig {

    @Bean
    FilterRegistrationBean<JwtValidationFilter> jwtFilter(JwtValidationFilter jwtValidationFilter) {
        
        FilterRegistrationBean<JwtValidationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(jwtValidationFilter);

        registrationBean.addUrlPatterns("/api/products/*");
        
        return registrationBean;
    }
    
}
