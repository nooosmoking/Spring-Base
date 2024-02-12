package edu.school21.config;

import edu.school21.repositories.UsersRepositoryJdbcImpl;
import edu.school21.repositories.UsersRepositoryJdbcTemplateImpl;
import edu.school21.services.UsersService;
import edu.school21.services.UsersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class TestApplicationConfig {
    @Bean
    DataSource dataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .build();

        return dataSource;
    }


    @Bean("usersServiceJdbc")
    UsersService usersServiceJdbc(DataSource dataSource) {
        return new UsersServiceImpl(new UsersRepositoryJdbcImpl(dataSource()));
    }

    @Bean("usersServiceJdbcTemplate")
    UsersService usersServiceJdbcTemplate(DataSource dataSource) {
        return new UsersServiceImpl(new UsersRepositoryJdbcTemplateImpl(dataSource()));
    }
}
