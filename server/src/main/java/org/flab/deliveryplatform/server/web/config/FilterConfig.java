package org.flab.deliveryplatform.server.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.Filter;
import org.flab.deliveryplatform.server.auth.AuthorizationServiceFactory;
import org.flab.deliveryplatform.server.web.filter.AuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> authorizationFilter(AuthorizationServiceFactory authorizationServiceFactory,
        ObjectMapper objectMapper) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(
            new AuthorizationFilter(objectMapper, authorizationServiceFactory));
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }

}
