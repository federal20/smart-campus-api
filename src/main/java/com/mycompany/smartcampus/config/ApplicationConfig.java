package com.mycompany.smartcampus.config;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        packages(
                "com.mycompany.smartcampus.resource",
                "com.mycompany.smartcampus.mapper",
                "com.mycompany.smartcampus.filter"
        );
    }
}