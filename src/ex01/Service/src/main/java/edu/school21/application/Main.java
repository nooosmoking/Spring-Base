package edu.school21.application;

import edu.school21.models.User;
import edu.school21.repositories.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        System.out.println("Current users: " + usersRepository.findAll());

        usersRepository.update(new User(3L, "ddd@gmail.com"));
        System.out.println("Users after update: " + usersRepository.findAll());

        usersRepository.delete(1L);
        System.out.println("Users after delete: " + usersRepository.findAll());

        usersRepository.save(new User(4L, "eee@gmail.com"));
        System.out.println("Users after save: " + usersRepository.findAll());

        System.out.println("User with email eee@gmail.com: " + (usersRepository.findByEmail("eee@gmail.com").isPresent() ? usersRepository.findByEmail("eee@gmail.com").get() : "none"));

        System.out.println("User with id 2: " + (usersRepository.findById(2L).isPresent() ? usersRepository.findById(2L).get() : "none"));
        System.out.println();

        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println("Current users at jdbcTemplate: " + usersRepository.findAll());
        System.out.println("User with id 2: " + (usersRepository.findById(2L).isPresent() ? usersRepository.findById(2L).get() : "none"));

        System.out.println("User with email eee@gmail.com: " + (usersRepository.findByEmail("eee@gmail.com").isPresent() ? usersRepository.findByEmail("eee@gmail.com").get() : "none"));
        usersRepository.save(new User(5L, "fff@gmail.com"));
        System.out.println("Users after save: " + usersRepository.findAll());

        usersRepository.update(new User(5L, "jjj@gmail.com"));
        System.out.println("User 5 after update: " + usersRepository.findById(5L));

        usersRepository.delete(5L);
        System.out.println("Users after delete: " + usersRepository.findAll());

    }
}
