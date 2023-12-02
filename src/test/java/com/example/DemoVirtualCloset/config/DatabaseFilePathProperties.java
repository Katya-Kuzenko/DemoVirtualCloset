package com.example.DemoVirtualCloset.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties("database.files")
@Component
public class DatabaseFilePathProperties {
    private Map<String, String> path;

    public Map<String, String> getPath() {
        return path;
    }

    public void setPath(Map<String, String> path) {
        this.path = path;
    }

    public String getUsersPath() {
        return path.get("users");
    }
}