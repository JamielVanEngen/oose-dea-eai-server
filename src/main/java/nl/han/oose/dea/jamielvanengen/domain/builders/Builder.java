package nl.han.oose.dea.jamielvanengen.domain.builders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Builder<T> {
    public List<T> buildObjectFromResultSet(ResultSet resultSet) throws SQLException;
}
