package nl.han.oose.dea.jamielvanengen.domain.factories.implementation;

import nl.han.oose.dea.jamielvanengen.domain.Playlist;
import nl.han.oose.dea.jamielvanengen.domain.factories.DomainFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDomainFactory implements DomainFactory<Playlist> {
    @Override
    public List<Playlist> getDomainObjectFromResultSet(ResultSet resultSet) throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        while (resultSet.next()) {
            playlists.add(new Playlist(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("userid")));
        }
        return playlists;
    }
}
