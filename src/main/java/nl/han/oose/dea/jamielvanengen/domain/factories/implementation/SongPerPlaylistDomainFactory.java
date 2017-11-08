package nl.han.oose.dea.jamielvanengen.domain.builders.implementation;

import nl.han.oose.dea.jamielvanengen.domain.builders.Builder;
import nl.han.oose.dea.jamielvanengen.domain.track.TrackPerPlaylist;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Song;

import javax.enterprise.inject.Default;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
@Named("SongPerPlaylistBuilder")
public class SongPerPlaylistBuilder implements Builder<TrackPerPlaylist> {
    @Override
    public List<TrackPerPlaylist> buildObjectFromResultSet(ResultSet resultSet) throws SQLException {
        List<TrackPerPlaylist> trackPerPlaylists = new ArrayList<>();
        while (resultSet.next()) {
            trackPerPlaylists.add(new TrackPerPlaylist( new Song(
                    resultSet.getInt("id"),
                    resultSet.getString("performer"),
                    resultSet.getString("titel"),
                    resultSet.getString("url"),
                    resultSet.getInt("afspeelduur"),
                    resultSet.getObject( "playcount" , Integer.class ),
                    resultSet.getString("album")), resultSet.getBoolean("isAvailableOffline")));
        }
        return trackPerPlaylists;
    }
}
