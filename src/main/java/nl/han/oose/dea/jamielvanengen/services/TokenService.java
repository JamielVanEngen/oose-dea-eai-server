package nl.han.oose.dea.jamielvanengen.services;

import nl.han.oose.dea.jamielvanengen.datasources.daos.implementation.TokenDAO;
import nl.han.oose.dea.jamielvanengen.domain.Token;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Default
public class TokenService {
    private static final String TIMEZONE = "Europe/Amsterdam";
    @Inject
    TokenDAO tokenDAO;

    public Token setToken(int userId) {
        String uuid = UUID.randomUUID().toString();
        LocalDate now = LocalDate.now(ZoneId.of(TIMEZONE));
        deleteExistingTokensOfUser(userId);
        try {
            tokenDAO.insertToken(uuid, userId, now);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Token(uuid, userId, now);
    }

    public int getUserIdByTokenUuid(String uuid) {
        Token token = new Token();
        try {
            token =  tokenDAO.getTokenByUuid(uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return token.getUserId();
    }

    private void deleteExistingTokensOfUser(int userId) {
        try {
            List<Token> existingTokens = tokenDAO.getTokensByUserId(userId);
            if (existingTokens.size() > 0) {
                existingTokens.stream().forEach(token -> tokenDAO.deleteTokenByUuid(token.getUuid()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean doesTokenExist(String uuid) {
        Token token = new Token();
        try {
            token =  tokenDAO.getTokenByUuid(uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uuid.equals(token.getUuid());
    }
}