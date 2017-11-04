package nl.han.oose.dea.jamielvanengen.domain.builders;

import nl.han.oose.dea.jamielvanengen.domain.track.impl.Video;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Default
public class VideoBuilder implements Builder<Video> {
    @Override
    public List<Video> buildObjectFromResultSet(ResultSet resultSet) throws SQLException {
        List<Video> songs = new ArrayList<>();
        while (resultSet.next()) {
            songs.add(new Video(
                    resultSet.getInt("id"),
                    resultSet.getString("performer"),
                    resultSet.getString("titel"),
                    resultSet.getString("url"),
                    resultSet.getInt("afspeelduur"),
                    resultSet.getObject( "playcount" , Integer.class ),
                    resultSet.getString("beschrijving"),
                    resultSet.getObject( "publicatiedatum" , LocalDate.class )));
        }
        return songs;
    }
}
