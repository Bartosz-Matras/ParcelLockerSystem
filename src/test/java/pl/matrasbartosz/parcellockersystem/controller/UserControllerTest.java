package pl.matrasbartosz.parcellockersystem.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.matrasbartosz.parcellockersystem.entity.user.UserDTO;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int randomServerPort;

    @BeforeEach
    void setTestRestTemplate() {
        this.testRestTemplate = new TestRestTemplate();
    }

    @Test
    void shouldSendPostRequestAndCreateUser() { //currently not work due to lack of authentication
        //given
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/users";
        UserDTO newUser = new UserDTO(0L,"test@gmail.com","Amu","Nowak","Test123","123456789",
               "Polska","Małopolskie","Kraków","30-000","Długa","11","2");

        //when
        ResponseEntity<UserDTO> response = testRestTemplate.postForEntity(baseUrl, newUser, UserDTO.class);

        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        UserDTO returnedUserDTO = response.getBody();
        assert returnedUserDTO != null;
        assertThat(returnedUserDTO.getId(), is(notNullValue()));
        assertThat(returnedUserDTO.getEmail(), equalTo("test@gmail.com"));
        assertThat(returnedUserDTO.getName(), equalTo("Amu"));
    }


}
