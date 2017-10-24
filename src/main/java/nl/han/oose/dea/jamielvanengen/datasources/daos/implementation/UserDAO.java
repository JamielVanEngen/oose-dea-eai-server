package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.User;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Default
public class UserDAO extends DAO {

    public UserDAO() {

    }

    public User getUserByUsername(String username) {
        try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            ArrayList<User> users = getUsersFromResultSet(rs);
            return users.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<User> getUsersFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add((new User(rs.getString("name"), rs.getString("password"), "123")));
        }

        return users;
    }
}
