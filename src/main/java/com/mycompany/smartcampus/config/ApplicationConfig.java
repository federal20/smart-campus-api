package com.mycompany.smartcampus.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/v1")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        packages(
                "com.mycompany.smartcampus.resource",
                "com.mycompany.smartcampus.mapper",
                "com.mycompany.smartcampus.filter"
        );
    }
}