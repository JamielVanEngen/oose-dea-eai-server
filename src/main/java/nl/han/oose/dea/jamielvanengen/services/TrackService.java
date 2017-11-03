package nl.han.oose.dea.jamielvanengen.services;

import nl.han.oose.dea.jamielvanengen.datasources.daos.implementation.SongDAO;
import nl.han.oose.dea.jamielvanengen.datasources.daos.implementation.TrackDAO;
import nl.han.oose.dea.jamielvanengen.datasources.daos.implementation.VideoDAO;
import nl.han.oose.dea.jamielvanengen.domain.track.Track;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackService {
    @Inject
    TrackDAO trackDAO;

    @Inject
    VideoDAO videoDAO;

    @Inject
    SongDAO songDAO;

    public int getTotalTrackTime() {
        int totalTrackTime = 0;
        try {
            totalTrackTime = trackDAO.getTotalTrackTime();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalTrackTime;
    }

    public List<Track> getAllTracksByPlaylistId(int playlistId) {
        List<Track> tracks = new ArrayList<>();
        try {
            tracks.addAll(songDAO.getAllSongsByPlaylistId(playlistId));
            tracks.addAll(videoDAO.getAllVideosByPlaylistId(playlistId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tracks;
    }
}
