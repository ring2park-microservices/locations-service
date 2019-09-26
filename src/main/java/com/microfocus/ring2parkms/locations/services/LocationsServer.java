package com.microfocus.ring2parkms.locations.services;

import com.microfocus.ring2parkms.locations.LocationRepository;
import com.microfocus.ring2parkms.locations.LocationsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import java.util.logging.Logger;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link LocationsConfiguration}. This is a deliberate separation of concerns.
 *
 * @author Kevin A. Lee
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(LocationsConfiguration.class)
public class LocationsServer {

    @Autowired
    protected LocationRepository locationRepository;

    protected Logger logger = Logger.getLogger(LocationsServer.class.getName());

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for locations-server.properties or
        // locations-server.yml
        System.setProperty("spring.config.name", "locations-server");

        SpringApplication.run(LocationsServer.class, args);
    }
}
