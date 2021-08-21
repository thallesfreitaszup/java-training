package com.example.socialmediakafka.integration

import com.example.socialmediakafka.api.request.UserRequest
import com.example.socialmediakafka.api.response.UserResponse
import com.example.socialmediakafka.repository.UserRepository
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

    @Autowired
    UserRepository userRepository
    def 'should not create an empty user' () {

        given:
         def userRequest = new UserRequest("", "", "", "", 0)
        when:
        def response = restTemplate.postForEntity("/users", userRequest, UserResponse.class)
        then:
        assert response.statusCode.value() == 400


    }

    def 'should create a valid user' () {

        given:
        def userRequest = new UserRequest("user", "user@email,com.br", "password", "city", 10)
        when:
        def response = restTemplate.postForEntity("/users", userRequest, UserResponse.class)
        then:

        assert response.statusCode.value() == 201
        def userId = response.body.id
        def optionalUser =  userRepository.findById(userId)
        assert optionalUser.isPresent()
        def user =  optionalUser.get()
        assert user.name == userRequest.name
        assert user.email == userRequest.email
        assert user.city == userRequest.city
        assert user.password == userRequest.password
        assert user.age == userRequest.age
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