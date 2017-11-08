package nl.han.oose.dea.jamielvanengen.domain.factories.implementation;

import nl.han.oose.dea.jamielvanengen.domain.Token;
import nl.han.oose.dea.jamielvanengen.domain.factories.DomainFactory;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Default
public class TokenDomainFactory implements DomainFactory<Token> {
    @Override
    public List<Token> getDomainObjectFromResultSet(ResultSet resultSet) throws SQLException {
        List<Token> tokens = new ArrayList<>();
        while (resultSet.next()) {
            tokens.add((new Token(resultSet.getString("token"),
                    resultSet.getInt("userid"),
                    resultSet.getObject( "date" , LocalDate.class ))));
        }
        return tokens;
    }
}
