package nl.han.oose.dea.jamielvanengen.domain.builders;

import nl.han.oose.dea.jamielvanengen.domain.Playlist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistBuilder {
    public List<Playlist> getPlaylistsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        while (resultSet.next()) {
            playlists.add(new Playlist(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("userid")));
        }
        return playlists;
    }
}
