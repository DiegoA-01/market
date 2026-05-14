package com.proyect.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import com.proyect.products.filter.JwtValidationFilter;

@Configuration
public class FilterConfig {

    
    @Bean
    public FilterRegistrationBean<JwtValidationFilter> disableDefaultRegistration(JwtValidationFilter filter) {
        FilterRegistrationBean<JwtValidationFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    
    @Bean
    public FilterRegistrationBean<JwtValidationFilter> customJwtFilter(JwtValidationFilter filter) {
        FilterRegistrationBean<JwtValidationFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setFilter(filter);
        // Aplica a todas las subrutas de products (ej. /api/v1/products/secure)
        registrationBean.addUrlPatterns("/products/*", "/users/*", "/sales/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
