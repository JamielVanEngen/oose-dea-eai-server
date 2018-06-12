package nl.han.oose.dea.jamielvanengen.domain.factories.implementation;

import nl.han.oose.dea.jamielvanengen.domain.User;
import nl.han.oose.dea.jamielvanengen.domain.factories.DomainFactory;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class UserBuilder implements DomainFactory<User> {
    public List<User> getDomainObjectFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add((new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("password"))));
        }
        return users;
    }
}
