package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.Playlist;
import nl.han.oose.dea.jamielvanengen.domain.builders.Builder;
import nl.han.oose.dea.jamielvanengen.domain.builders.PlaylistBuilder;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PlaylistDAO extends DAO {
    @Inject
    Builder<Playlist> playlistBuilder;


    public List<Playlist> getAllPlaylists() throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlist");
        return playlistBuilder.buildObjectFromResultSet(statement.executeQuery());
    }

    public void deletePlaylistById(int id) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist WHERE id = ?");
        statement.setInt(1, id);

        statement.executeUpdate();
    }

    public Playlist getPlaylistById(int id) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlist WHERE id = ?");
        statement.setInt(1, id);
        List<Playlist> playlists = playlistBuilder.buildObjectFromResultSet(statement.executeQuery());
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
}
