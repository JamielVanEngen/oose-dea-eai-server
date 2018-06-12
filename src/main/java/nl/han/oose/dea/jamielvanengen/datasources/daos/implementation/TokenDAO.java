package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.Token;
import nl.han.oose.dea.jamielvanengen.domain.factories.implementation.TokenDomainFactory;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Default
public class TokenDAO extends DAO {
    @Inject
    TokenDomainFactory tokenBuilder;

    public void insertToken(String uuid, int id, LocalDate date) throws SQLException {
        Connection conn = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO token(token, userid, date) VALUES(?, ?, ?)");
        statement.setString(1, uuid);
        statement.setInt(2, id);
        statement.setObject(3, date);

        statement.executeUpdate();
        statement.close();
    }

    public Token getTokenByUuid(String uuid) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM token WHERE token = ?");
        statement.setString(1, uuid);
        List<Token> foundTokens = tokenBuilder.getDomainObjectFromResultSet(statement.executeQuery());
        statement.close();
        return foundTokens.stream().findFirst().orElse(new Token());
    }

    public List<Token> getTokensByUserId(int userId) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM token WHERE userid = ?");
        statement.setInt(1, userId);
        List<Token> foundTokens = tokenBuilder.getDomainObjectFromResultSet(statement.executeQuery());
        statement.close();
        return foundTokens;
    }

    public void deleteTokenByUuid(String uuid) {
        Connection conn = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("DELETE FROM token WHERE token = ?");
            statement.setString(1, uuid);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
