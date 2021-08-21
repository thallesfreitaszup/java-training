package com.example.socialmediakafka.integration

import com.example.socialmediakafka.api.request.UserRequest
import com.example.socialmediakafka.api.response.UserResponse
import org.junit.ClassRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [ Initializer.class ])
class CreateUserTest extends Specification {
    @ClassRule
    @Shared
    static GenericContainer postgreSQLContainer = ContainerUtils.getPostgres()
    @Autowired
    TestRestTemplate restTemplate
    def 'should not create an empty user' () {

        given:
         def userRequest = new UserRequest("", "", "", "", 20)
        when:
        def response = restTemplate.postForEntity("/users", userRequest, UserResponse.class)
        then:
        assert response.statusCode.value() == 400


    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment())
        }
    }
}