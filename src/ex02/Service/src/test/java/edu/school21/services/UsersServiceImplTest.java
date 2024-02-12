package edu.school21.services;

import edu.school21.config.TestApplicationConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class UsersServiceImplTest {
    private static UsersService usersServiceJdbc;
    private static UsersService usersServiceJdbcTemplate;


    @BeforeAll
    static void before() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        usersServiceJdbc = context.getBean("usersServiceJdbc", UsersService.class);
        usersServiceJdbcTemplate = context.getBean("usersServiceJdbcTemplate", UsersService.class);
    }

    @Test
    public void signUpJdbc() {
        assertEquals(usersServiceJdbc.SignUp("rrr@gmail.com"), "tmp_password");
    }

    @Test
    public void signUpJdbcTemplate() {
        assertEquals(usersServiceJdbcTemplate.SignUp("rrz@gmail.com"), "tmp_password");
    }
}
