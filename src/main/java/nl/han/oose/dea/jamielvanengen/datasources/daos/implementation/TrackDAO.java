package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.tracks.Track;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackDAO extends DAO {
    public int getTotalTrackTime() throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("SELECT SUM(total.afspeelduur) AS afspeelduur FROM (" +
                "SELECT afspeelduur FROM spotitube.song" +
                " UNION ALL" +
                " SELECT afspeelduur FROM spotitube.video)" +
                "total");

        int afspeelduur = getAfspeelduurFromResultSet(statement.executeQuery());
        statement.close();
        return afspeelduur;
    }

    private int getAfspeelduurFromResultSet(ResultSet resultSet) throws SQLException {
        List<Integer> result = new ArrayList<Integer>();
        while (resultSet.next()) {
            result.add(resultSet.getInt("afspeelduur"));
        }
        return result.stream().findFirst().orElse(0);
    }
}
