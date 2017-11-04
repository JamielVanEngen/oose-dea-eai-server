package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.builders.Builder;
import nl.han.oose.dea.jamielvanengen.domain.track.Track;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Song;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Default
public class SongDAO extends DAO {
    @Inject
    Builder<Song> songBuilder;

    public List<Song> getAllSongsByPlaylistId(int playlistId) throws SQLException {
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
        return songBuilder.buildObjectFromResultSet(statement.executeQuery());
    }

    public List<Song> getAllSongsNotInPlaylist(int playlistId) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT *\n" +
                        "FROM spotitube.song\n" +
                        "WHERE id NOT IN " +
                        " (SELECT DISTINCT trackId " +
                            "FROM spotitube.`songs-per-playlist` " +
                        " WHERE playlistId = ?)"
        );
        statement.setInt(1, playlistId);
        return songBuilder.buildObjectFromResultSet(statement.executeQuery());
    }
}
