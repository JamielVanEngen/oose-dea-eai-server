package nl.han.oose.dea.jamielvanengen.domain.builders.implementation;

import nl.han.oose.dea.jamielvanengen.domain.Playlist;
import nl.han.oose.dea.jamielvanengen.domain.builders.Builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistBuilder implements Builder<Playlist> {
    @Override
    public List<Playlist> buildObjectFromResultSet(ResultSet resultSet) throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        while (resultSet.next()) {
            playlists.add(new Playlist(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("userid")));
        }
        return playlists;
    }
}
