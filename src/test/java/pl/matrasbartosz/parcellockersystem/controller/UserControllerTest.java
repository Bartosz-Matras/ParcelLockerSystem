package pl.matrasbartosz.parcellockersystem.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pl.matrasbartosz.parcellockersystem.entity.user.mappers.UserDTO;
import pl.matrasbartosz.parcellockersystem.repository.user.UserRepository;

import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    public static final String ALREADY_EXISTS = "User with given email or phone number already exists.";
    private String baseUrl;
    private UserDTO newUser;
    private static final Function<String, String> toBaseUrl = ("http://localhost:%s/api/v1/users")::formatted;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @LocalServerPort
    int randomServerPort;

    @BeforeEach
    void setTestRestTemplate() {
        this.baseUrl = toBaseUrl.apply(String.valueOf(randomServerPort));
        this.newUser = new UserDTO(0L,"XX@gmail.com","XX","XX","123","XX",
                "XX","XX","XX","XX","XX","XX","XX");
    }


    @Test
    void postRequestShouldCreateUser() {
        //given
        //when
        ResponseEntity<UserDTO> response = testRestTemplate.postForEntity(baseUrl, newUser, UserDTO.class);

        //then
        assertEquals(CREATED, response.getStatusCode());

        UserDTO returnedUserDTO = response.getBody();
        assert returnedUserDTO != null;
        assertThat(returnedUserDTO.id(), is(notNullValue()));
        assertThat(returnedUserDTO.email(), equalTo("XX@gmail.com"));
        assertThat(returnedUserDTO.name(), equalTo("XX"));
        assertThat(response.getStatusCode(), equalTo(CREATED));
    }

    @Test
    void postRequestShouldNotCreateUser() {
        //given
        testRestTemplate.postForEntity(baseUrl, newUser, UserDTO.class);

        //when
        ResponseEntity<String> responseConflict = testRestTemplate.postForEntity(baseUrl, newUser, String.class);

        //then
        assertEquals(CONFLICT, responseConflict.getStatusCode());
        assertThat(responseConflict.getBody(), equalTo(ALREADY_EXISTS));
    }

    @Test
    void shouldReturnUnauthorizedForGetMethod() {
        //given
        //when
        ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl, String.class);

        //then
        assertThat(response.getStatusCode(), equalTo(UNAUTHORIZED));
    }

    @Test
    void shouldReturnUnauthorizedForInvalidUserCredentials() {
        //given
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("bartek@gmail.com", "123");
        HttpEntity<?> entity = new HttpEntity<>(headers);

        //when
        ResponseEntity<String> response = testRestTemplate.exchange(baseUrl, HttpMethod.GET, entity, String.class);

        //then
        assertThat(response.getStatusCode(), equalTo(UNAUTHORIZED));
    }




}
