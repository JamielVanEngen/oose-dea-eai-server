package nl.han.oose.dea.jamielvanengen.domain.builders;

import nl.han.oose.dea.jamielvanengen.domain.User;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class UserBuilder {
    public List<User> getUsersFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add((new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("password"), "123")));
        }
        return users;
    }
}
