package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.track.Track;
import nl.han.oose.dea.jamielvanengen.domain.builders.Builder;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
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
        PreparedStatement statement = connection.prepareStatement("CALL spotitube.`get_total_length_from_all_playlists`;");

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
