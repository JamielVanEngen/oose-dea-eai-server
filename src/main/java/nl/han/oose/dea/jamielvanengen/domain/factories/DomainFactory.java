package nl.han.oose.dea.jamielvanengen.domain.factories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DomainFactory<T> {
    public List<T> getDomainObjectFromResultSet(ResultSet resultSet) throws SQLException;
}
