package nl.han.oose.dea.jamielvanengen.domain.builders;

import nl.han.oose.dea.jamielvanengen.domain.Track;

import javax.enterprise.inject.Default;
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
            tracks.add(new Track(
                    resultSet.getInt("id"),
                    resultSet.getString("performer"),
                    resultSet.getString("titel"),
                    resultSet.getString("url"),
                    resultSet.getString("album"),
                    resultSet.getString("beschrijving"),
                    resultSet.getObject( "publicatiedatum" , LocalDate.class ),
                    resultSet.getInt("afspeelduur"),
                    resultSet.getObject( "playcount" , Integer.class )));
        }
        return tracks;
    }
}
