package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.Playlist;
import nl.han.oose.dea.jamielvanengen.domain.builders.PlaylistBuilder;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PlaylistDAO extends DAO {
    @Inject
    PlaylistBuilder playlistBuilder;


    public List<Playlist> getAllPlaylists() throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlist");
        return playlistBuilder.getPlaylistsFromResultSet(statement.executeQuery());
    }
}
