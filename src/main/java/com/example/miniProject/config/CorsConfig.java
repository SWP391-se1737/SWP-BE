package com.example.miniProject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500/") // Specify your allowed origin(s) here
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify the allowed HTTP methods
                .allowedHeaders("*") // Specify the allowed headers
                .allowCredentials(true) ;// Allow sending credentials like cookies
    }
}
