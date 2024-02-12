package edu.school21.repositories;

import edu.school21.models.User;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UsersRepositoryJdbcImpl implements UsersRepository{
    private final DataSource dataSource;

    @Override
    public Optional<User> findById(Long id) {
        String query = "SELECT * FROM \"user\" WHERE id = " + id;
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()
        ) {
            ResultSet result = st.executeQuery(query);
            if (!result.next()) {
                return Optional.empty();
            }
            String email = result.getString("email");
            return Optional.of(new User(id, email));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM \"user\";";
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()
        ) {
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                Long id = result.getLong("id");
                String email = result.getString("email");
                User currUser = new User(id, email);
                userList.add(currUser);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        return userList;
    }

    @Override
    public void save(User entity) {
        String query = "INSERT INTO \"user\" (email) VALUES (?);";
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(query);
        ) {
            st.setString(1, entity.getEmail());
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
    }

    @Override
    public void update(User entity) {
        String query = "UPDATE \"user\" SET email = ? WHERE id = ?;";
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(query);
        ) {
            st.setString(1, entity.getEmail());
            st.setLong(2, entity.getId());
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
    }


    @Override
    public void delete(Long id) {
        String query = "DELETE FROM \"user\" WHERE id = ?;";
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(query);
        ) {
            st.setLong(1, id);
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "SELECT * FROM \"user\" WHERE email = '" + email+ "'";
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()
        ) {
            ResultSet result = st.executeQuery(query);
            if (!result.next()) {
                return Optional.empty();
            }
            Long id = result.getLong("id");
            return Optional.of(new User(id, email));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        return Optional.empty();
    }
}
