package com.ssafy.togetherhomt.config.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Configuration
public class GlobalConfig {

    private ApplicationContext context;
    private ResourceLoader resourceLoader;
    private String uploadFilePath;
    private String uploadResourcePath;

    @Autowired
    public GlobalConfig(ApplicationContext context, ResourceLoader resourceLoader, String uploadFilePath, String uploadResourcePath) {
        this.context = context;
        this.resourceLoader = resourceLoader;
        this.uploadFilePath = uploadFilePath;
        this.uploadResourcePath = uploadResourcePath;
    }

    @PostConstruct
    public void init(){
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        String activeProfile = "local";
        if(!ObjectUtils.isEmpty(activeProfiles)){
            activeProfile = activeProfiles[0];
        }
        String resourcePath = String.format("classpath:globals/global-%s.properties",activeProfile);
        try{
            Resource resource = resourceLoader.getResource(resourcePath);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);

            this.uploadFilePath = properties.getProperty("uploadFile.path");
            System.out.println("properties.getProperty(\"uploadFile.resourcePath\") = " + properties.getProperty("uploadFile.resourcePath"));
            this.uploadResourcePath = properties.getProperty("uploadFile.resourcePath");
        }catch (Exception e){
            System.out.println("e = " + e);
        }
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public String getUploadResourcePath() {
        return uploadResourcePath;
    }
}
