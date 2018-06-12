package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.factories.DomainFactory;
import nl.han.oose.dea.jamielvanengen.domain.track.TrackPerPlaylist;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Video;

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
    DomainFactory<Video> videoDomainFactory;

    @Inject
    @Named("VideoPerPlaylistBuilder")
    DomainFactory<TrackPerPlaylist> trackPerPlaylistDomainFactory;

    public List<TrackPerPlaylist> getAllVideosByPlaylistId(int playlistId) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT t.id,\n" +
                        "t.performer,\n" +
                        "t.titel,\n" +
                        "v.publicatiedatum,\n" +
                        "v.beschrijving,\n" +
                        "t.url,\n" +
                        "t.afspeelduur,\n" +
                        "t.playcount,\n" +
                        "tpp.isAvailableOffline\n" +
                        "FROM track t\n" +
                        "INNER JOIN video v ON t.id = v.id\n" +
                        "INNER JOIN track_per_playlist tpp ON t.id = tpp.trackid\n" +
                        "WHERE tpp.playlistid = ?"
        );
        statement.setInt(1, playlistId);
        List<TrackPerPlaylist> trackPerPlaylists = trackPerPlaylistDomainFactory
                .getDomainObjectFromResultSet(statement.executeQuery());
        statement.close();
        return trackPerPlaylists;
    }

    public List<Video> getAllVideosNotInPlaylist(int playlistId) throws SQLException {
        Connection connection = connectionFactory.getConnectionFromProperties();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT t.id,\n" +
                        "t.performer,\n" +
                        "t.titel,\n" +
                        "v.publicatiedatum,\n" +
                        "v.beschrijving,\n" +
                        "t.url,\n" +
                        "t.afspeelduur,\n" +
                        "t.playcount\n" +
                        "FROM track t\n" +
                        "INNER JOIN video v ON t.id = v.id\n" +
                        "WHERE t.id NOT IN (\n" +
                        "\tSELECT DISTINCT trackid\n" +
                        "\t\tFROM track_per_playlist\n" +
                        "\tWHERE playlistid = ?)"
        );
        statement.setInt(1, playlistId);
        List<Video> videos = videoDomainFactory.getDomainObjectFromResultSet(statement.executeQuery());
        statement.close();
        return videos;
    }
}
