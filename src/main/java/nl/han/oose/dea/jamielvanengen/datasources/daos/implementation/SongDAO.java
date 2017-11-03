package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import java.sql.ResultSet;
import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.builders.Builder;
import nl.han.oose.dea.jamielvanengen.domain.track.Track;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongDAO extends TrackDAO {
    @Inject
    Builder<Track> trackBuilder;

    public List<Track> getAllSongsByPlaylistId(int playlistId) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT s.id,\n" +
                        "\t s.performer,\n" +
                        "\t s.titel,\n" +
                        "\t s.album,\n" +
                        "\t s.url,\n" +
                        "\t s.afspeelduur,\n" +
                        "\t s.playcount,\n" +
                        "    spp.isAvailableOffline\n" +
                        "FROM song s\n" +
                        "INNER JOIN `songs-per-playlist` spp ON  s.id = spp.trackid\n" +
                        "WHERE spp.playlistid = ?"
        );
        statement.setInt(1, playlistId);
        return trackBuilder.buildObjectFromResultSet(statement.executeQuery());
    }
}