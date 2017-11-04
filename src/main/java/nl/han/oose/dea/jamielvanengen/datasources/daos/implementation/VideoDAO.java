package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.builders.Builder;
import nl.han.oose.dea.jamielvanengen.domain.track.Track;
import nl.han.oose.dea.jamielvanengen.domain.track.TrackPerPlaylist;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Default
public class VideoDAO extends DAO {
    @Inject
    Builder<Track> trackBuilder;

    @Inject
    @Named("VideoPerPlaylist")
    Builder<TrackPerPlaylist> trackPerPlaylistBuilder;

    public List<Track> getAllVideosByPlaylistId(int playlistId) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT v.id,\n" +
                        "\t v.performer,\n" +
                        "\t v.titel,\n" +
                        "\t v.publicatiedatum,\n" +
                        "\t v.beschrijving,\n" +
                        "\t v.url,\n" +
                        "\t v.afspeelduur,\n" +
                        "\t v.playcount,\n" +
                        "    vpp.isAvailableOffline\n" +
                        "FROM video v\n" +
                        "INNER JOIN `videos-per-playlist` vpp ON  v.id = vpp.trackid\n" +
                        "WHERE vpp.playlistid = ?"
        );
        statement.setInt(1, playlistId);
        return trackBuilder.buildObjectFromResultSet(statement.executeQuery());
    }

    public List<Track> getAllVideosNotInPlaylist(int playlistId) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT *\n" +
                        "FROM spotitube.video\n" +
                        "WHERE id NOT IN " +
                        " (SELECT DISTINCT trackId " +
                        "FROM spotitube.`videos-per-playlist` " +
                        " WHERE playlistId = ?)"
        );
        statement.setInt(1, playlistId);
        return trackBuilder.buildObjectFromResultSet(statement.executeQuery());
    }
}
