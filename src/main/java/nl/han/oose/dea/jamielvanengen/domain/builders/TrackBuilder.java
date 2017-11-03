package nl.han.oose.dea.jamielvanengen.domain.builders;

import nl.han.oose.dea.jamielvanengen.domain.track.Track;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Song;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Video;

import javax.ejb.Local;
import javax.enterprise.inject.Default;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackBuilder implements Builder<Track> {

    @Override
    public List<Track> buildObjectFromResultSet(ResultSet resultSet) throws SQLException {
        List<Track> tracks = new ArrayList<>();
        while (resultSet.next()) {
            if (isResultSetASong(resultSet)) {
                tracks.add(buildSongFromResultSet(resultSet));
            }
            else {
                tracks.add(buildVideoFromResultSet(resultSet));
            }
        }
        return tracks;
    }

    private boolean isResultSetASong(ResultSet resultSet) throws SQLException {
        return resultSet.getString("album") != null ? true: false;
    }

    private Video buildVideoFromResultSet(ResultSet resultSet) throws SQLException {
        return new Video(
                resultSet.getInt("id"),
                resultSet.getString("performer"),
                resultSet.getString("titel"),
                resultSet.getString("url"),
                resultSet.getInt("afspeelduur"),
                resultSet.getObject( "playcount" , Integer.class ),
                resultSet.getBoolean("isAvailableOffline"),
                resultSet.getString("beschrijving"),
                resultSet.getObject( "publicatiedatum" , LocalDate.class ));
    }
    private Song buildSongFromResultSet(ResultSet resultSet) throws SQLException {
        return new Song(
                resultSet.getInt("id"),
                resultSet.getString("performer"),
                resultSet.getString("titel"),
                resultSet.getString("url"),
                resultSet.getInt("afspeelduur"),
                resultSet.getObject( "playcount" , Integer.class ),
                resultSet.getBoolean("isAvailableOffline"),
                resultSet.getString("album"));
    }
}
