package com.webpay.workpay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${path.img}")
    String pathImg;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String currentPath = String.format("%s%s%s", System.getProperty("user.dir"), File.separatorChar, pathImg + "/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///" + currentPath + "/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
}
