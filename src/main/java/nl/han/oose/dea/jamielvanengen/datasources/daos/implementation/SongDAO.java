package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.factories.DomainFactory;
import nl.han.oose.dea.jamielvanengen.domain.track.TrackPerPlaylist;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Song;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Default
public class SongDAO extends DAO {
    @Inject
    DomainFactory<Song> songDomainFactory;

    @Inject
    @Named("SongPerPlaylistBuilder")
    DomainFactory<TrackPerPlaylist> trackPerPlaylistDomainFactory;

    public List<TrackPerPlaylist> getAllSongsByPlaylistId(int playlistId) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT t.id,\n" +
                        "t.performer,\n" +
                        "t.titel,\n" +
                        "s.album,\n" +
                        "t.url,\n" +
                        "t.afspeelduur,\n" +
                        "t.playcount,\n" +
                        "tpp.isAvailableOffline\n" +
                        "FROM track t\n" +
                        "INNER JOIN song s ON t.id = s.id\n" +
                        "INNER JOIN track_per_playlist tpp ON t.id = tpp.trackid\n" +
                        "WHERE tpp.playlistid = ?"
        );
        statement.setInt(1, playlistId);
        List<TrackPerPlaylist> trackPerPlaylists = trackPerPlaylistDomainFactory
                .getDomainObjectFromResultSet(statement.executeQuery());
        statement.close();
        return trackPerPlaylists;
    }

    public List<Song> getAllSongsNotInPlaylist(int playlistId) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT t.id,\n" +
                        "t.performer,\n" +
                        "t.titel,\n" +
                        "s.album,\n" +
                        "t.url,\n" +
                        "t.afspeelduur,\n" +
                        "t.playcount\n" +
                        "FROM track t\n" +
                        "INNER JOIN song s ON t.id = s.id\n" +
                        "WHERE t.id NOT IN (\n" +
                        "\tSELECT DISTINCT trackid\n" +
                        "\t\tFROM track_per_playlist\n" +
                        "\tWHERE playlistid = ?)"
        );
        statement.setInt(1, playlistId);
        List<Song> songs = songDomainFactory.getDomainObjectFromResultSet(statement.executeQuery());
        statement.close();
        return songs;
    }
}
