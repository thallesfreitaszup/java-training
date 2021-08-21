package com.example.socialmediakafka.integration

import org.testcontainers.containers.PostgreSQLContainer

class ContainerUtils {
    static getPostgres(){
        def container = new PostgreSQLContainer("postgres:11.8-alpine")
                .withUsername("kafka")
                .withPassword("kafka")
                .withDatabaseName("kafka")
                .withExposedPorts(5432)
        container.start()
        return container
    }
}
