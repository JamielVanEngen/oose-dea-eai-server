package nl.han.oose.dea.jamielvanengen.datasources.daos.implementation;

import nl.han.oose.dea.jamielvanengen.datasources.daos.DAO;
import nl.han.oose.dea.jamielvanengen.domain.builders.Builder;
import nl.han.oose.dea.jamielvanengen.domain.track.Track;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class VideoDAO extends TrackDAO {
    @Inject
    Builder<Track> trackBuilder;

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
                        "    spp.isAvailableOffline\n" +
                        "FROM video v\n" +
                        "INNER JOIN `videos-per-playlist` vpp ON  v.id = vpp.trackid\n" +
                        "WHERE vpp.playlistid = ?"
        );
        statement.setInt(1, playlistId);
        return trackBuilder.buildObjectFromResultSet(statement.executeQuery());
    }
}
