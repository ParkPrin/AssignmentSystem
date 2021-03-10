package me.parkprin.assignment.config;


import me.parkprin.assignment.config.web.SimplePageRequest;
import me.parkprin.assignment.config.web.SimplePageRequestHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {

    @Bean
    public SimplePageRequestHandlerMethodArgumentResolver simplePageRequestHandlerMethodArgumentResolver() {
        return new SimplePageRequestHandlerMethodArgumentResolver(new SimplePageRequest());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(simplePageRequestHandlerMethodArgumentResolver());
    }

}
