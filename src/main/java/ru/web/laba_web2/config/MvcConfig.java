package ru.web.laba_web2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload_users.path}")
    private String uploadPath_users;
    @Value("${upload_offers.path}")
    private String uploadPath_offers;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads_users/**")
                .addResourceLocations("file:" + uploadPath_users + "/");
        registry.addResourceHandler("/uploads_offers/**")
                .addResourceLocations("file:" + uploadPath_offers + "/");
    }
}
