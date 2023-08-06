package pl.matrasbartosz.parcellockersystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import pl.matrasbartosz.parcellockersystem.controller.UserController;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class ParcelLockerSystemApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertThat(applicationContext, is(notNullValue()));
        validateControllers();
    }

    private void validateControllers() {
        UserController userController = applicationContext.getBean(UserController.class);
        assertThat(userController, is(notNullValue()));

    }

}
