package com.mycompany.smartcampus;

import com.mycompany.smartcampus.config.ApplicationConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8081/";

    public static HttpServer startServer() {
        return GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI),
                new ApplicationConfig()
        );
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = startServer();
        System.out.println("Smart Campus API running at: " + BASE_URI );
        System.out.println("Press Enter to stop the server...");
        System.in.read();
        server.shutdownNow();
    }
}