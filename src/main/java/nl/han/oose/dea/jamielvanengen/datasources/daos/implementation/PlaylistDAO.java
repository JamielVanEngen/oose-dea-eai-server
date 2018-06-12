package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.Playlist;
import nl.han.oose.dea.jamielvanengen.domain.factories.DomainFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PlaylistDAO extends DAO {
    @Inject
    DomainFactory<Playlist> playlistDomainFactory;


    public List<Playlist> getAllPlaylists() throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlist");

        List<Playlist> playlists = playlistDomainFactory.getDomainObjectFromResultSet(statement.executeQuery());
        statement.close();
        return playlists;
    }

    public void deletePlaylistById(int id) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist WHERE id = ?");
        statement.setInt(1, id);

        statement.executeUpdate();
        statement.close();
    }

    public Playlist getPlaylistById(int id) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlist WHERE id = ?");
        statement.setInt(1, id);
        List<Playlist> playlists = playlistDomainFactory.getDomainObjectFromResultSet(statement.executeQuery());
        statement.close();
        return playlists.stream().findFirst().orElse(new Playlist());
    }

    public void addPlaylist(String name, int userId) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO playlist(name, userid) VALUES(?, ?)");
        statement.setString(1, name);
        statement.setInt(2, userId);
        statement.executeUpdate();
        statement.close();
    }

    public void editPlaylist(int id, String name) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("UPDATE playlist SET name = ? WHERE id = ?");
        statement.setString(1, name);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }

    public void removeTrackFromPlaylist(int playlistId, int trackId) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM track_per_playlist WHERE playlistid = ? AND trackid = ?");
        statement.setInt(1, playlistId);
        statement.setInt(2, trackId);

        statement.executeUpdate();
        statement.close();
    }

    public void addTrackToPlaylist(int playlistId, int trackId, boolean isAvailableOffline) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO spotitube.track_per_playlist(playlistid, trackid, isAvailableOffline)\n" +
                "VALUES(?, ?, ?)");
        statement.setInt(1, playlistId);
        statement.setInt(2, trackId);
        statement.setBoolean(3, isAvailableOffline);

        statement.executeUpdate();
        statement.close();
    }
}
