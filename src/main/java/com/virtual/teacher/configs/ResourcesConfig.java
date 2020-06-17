package com.virtual.teacher.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/photos/**").addResourceLocations("file:photos/");
        registry.addResourceHandler("/coursePhotos/**").addResourceLocations("file:coursePhotos/");
        registry.addResourceHandler("/courseTasks/**").addResourceLocations("file:courseTasks/");
        registry.addResourceHandler("/courseSolutions/**").addResourceLocations("file:courseSolutions/");
    }
}
