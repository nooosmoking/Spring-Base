package edu.school21.repositories;

import edu.school21.models.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        String query = "SELECT * FROM \"user\" WHERE id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        User user = jdbcTemplate.queryForObject(query, parameters, new BeanPropertyRowMapper<>(User.class));
        if (user == null) {
            return Optional.empty();
        } else {
            return Optional.of(user);
        }
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM \"user\"";
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowUser = new User();
            rowUser.setId(r.getLong("id"));
            rowUser.setEmail(r.getString("email"));
            return rowUser;
        };
        return jdbcTemplate.query(query, userRowMapper);
    }

    @Override
    public void save(User entity) {
        String query = "INSERT INTO \"user\" (email) VALUES (:email);";
        jdbcTemplate.update(query, new MapSqlParameterSource().addValue("email", entity.getEmail()));
    }

    @Override
    public void update(User entity) {
        String query = "UPDATE \"user\" SET email = :email WHERE id = :id;";
        jdbcTemplate.update(query, new BeanPropertySqlParameterSource(entity));
    }


    @Override
    public void delete(Long id) {
        String query = "DELETE FROM \"user\" WHERE id = :id;";
        jdbcTemplate.update(query, new MapSqlParameterSource().addValue("id", id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "SELECT * FROM \"user\" WHERE email = :email";
        User user = jdbcTemplate.queryForObject(query, new MapSqlParameterSource().addValue("email", email), new BeanPropertyRowMapper<>(User.class));
        if (user == null) {
            return Optional.empty();
        } else {
            return Optional.of(user);
        }
    }
}
