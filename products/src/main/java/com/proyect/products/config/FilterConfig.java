package com.proyect.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import com.proyect.products.filter.JwtValidationFilter;

@Configuration
public class FilterConfig {

    /**
     * este metodo omite el registro automatico de los componentes de Springboot y asi poder configurar las rutas protegidas manualmente.
     * 
     * @param filter
     * @return
     */
    @Bean
    public FilterRegistrationBean<JwtValidationFilter> disableDefaultRegistration(JwtValidationFilter filter) {
        FilterRegistrationBean<JwtValidationFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    /**
     * valida los token antes de permitirle el acceso a laas rutas protegidas 
     * 
     * @param filter
     * @return
     */
    @Bean
    public FilterRegistrationBean<JwtValidationFilter> customJwtFilter(JwtValidationFilter filter) {
        FilterRegistrationBean<JwtValidationFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/products/*", "/users/*", "/sales/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
