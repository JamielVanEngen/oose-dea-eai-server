package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.builders.Builder;
import nl.han.oose.dea.jamielvanengen.domain.track.Track;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SongDAO extends DAO {
    @Inject
    Builder<Track> trackBuilder;

    public List<Track> getAllSongsByPlaylistId(int playlistId) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM song WHERE id IN \n" +
                "\t(SELECT trackid \n" +
                "    FROM `songs-per-playlist`\n" +
                "    WHERE playlistid = ?)");
        statement.setInt(1, playlistId);
        return trackBuilder.buildObjectFromResultSet(statement.executeQuery());
    }
}
