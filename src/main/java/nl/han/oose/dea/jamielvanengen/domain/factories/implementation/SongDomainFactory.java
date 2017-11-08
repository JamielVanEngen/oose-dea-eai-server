package nl.han.oose.dea.jamielvanengen.domain.builders.implementation;

import nl.han.oose.dea.jamielvanengen.domain.builders.Builder;
import nl.han.oose.dea.jamielvanengen.domain.track.Track;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Song;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class SongBuilder implements Builder<Song> {
    @Override
    public List<Song> buildObjectFromResultSet(ResultSet resultSet) throws SQLException {
        List<Song> songs = new ArrayList<>();
        while (resultSet.next()) {
            songs.add(new Song(
                    resultSet.getInt("id"),
                    resultSet.getString("performer"),
                    resultSet.getString("titel"),
                    resultSet.getString("url"),
                    resultSet.getInt("afspeelduur"),
                    resultSet.getObject( "playcount" , Integer.class ),
                    resultSet.getString("album")));
        }
        return songs;
    }
}
