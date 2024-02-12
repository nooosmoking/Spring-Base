package edu.school21.application;

import edu.school21.config.ApplicationConfig;
import edu.school21.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UsersService service = context.getBean(UsersService.class);

        System.out.println(service.SignUp("ggg@gmail.com"));
        System.out.println(service.SignUp("sss@gmail.com"));
    }
}
