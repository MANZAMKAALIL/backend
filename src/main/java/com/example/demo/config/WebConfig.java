package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve images from the "uploads" folder
        registry.addResourceHandler("/propertyImages/**")
                .addResourceLocations("file:E:/Real-Estate-Management-System-BackEnd-master/uploads/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow cross-origin requests for the "/propertyImages/**" path
        registry.addMapping("/propertyImages/**")
                .allowedOrigins("*") // Allow all origins, or you can specify certain domains
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow necessary HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials if needed
    }
}
