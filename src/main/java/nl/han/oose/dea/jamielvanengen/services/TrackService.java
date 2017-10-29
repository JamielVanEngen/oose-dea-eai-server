package nl.han.oose.dea.jamielvanengen.services;

import nl.han.oose.dea.jamielvanengen.datasources.daos.implementation.TrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.SQLException;

@Default
public class TrackService {
    @Inject
    TrackDAO trackDAO;

    public int getTotalTrackTime() {
        int totalTrackTime = 0;
        try {
            totalTrackTime = trackDAO.getTotalTrackTime();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalTrackTime;
    }
}
