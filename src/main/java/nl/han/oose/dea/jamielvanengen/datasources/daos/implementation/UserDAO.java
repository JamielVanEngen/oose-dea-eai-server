package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.User;
import nl.han.oose.dea.jamielvanengen.domain.builders.UserBuilder;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class UserDAO extends DAO {
    @Inject
    private UserBuilder userBuilder;

    public UserDAO() {

    }

    public User getUserByUsername(String username) {
        try {
            Connection conn = connectionFactory.getConnectionFromProperties();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            List<User> users = userBuilder.getUsersFromResultSet(rs);
            return users.stream().findFirst().orElseGet(() -> null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
