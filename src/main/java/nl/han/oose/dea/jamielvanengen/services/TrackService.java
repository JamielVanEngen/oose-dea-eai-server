package nl.han.oose.dea.jamielvanengen.services;

import nl.han.oose.dea.jamielvanengen.datasources.daos.implementation.SongDAO;
import nl.han.oose.dea.jamielvanengen.datasources.daos.implementation.TrackDAO;
import nl.han.oose.dea.jamielvanengen.datasources.daos.implementation.VideoDAO;
import nl.han.oose.dea.jamielvanengen.domain.track.Track;
import nl.han.oose.dea.jamielvanengen.domain.track.TrackPerPlaylist;
import nl.han.oose.dea.jamielvanengen.domain.track.impl.Song;

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

    public List<TrackPerPlaylist> getAllTracksByPlaylistId(int playlistId) {
        List<TrackPerPlaylist> trackPerPlaylists = new ArrayList<>();
        try {
            trackPerPlaylists.addAll(songDAO.getAllSongsByPlaylistId(playlistId));
            trackPerPlaylists.addAll(videoDAO.getAllVideosByPlaylistId(playlistId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trackPerPlaylists;
    }

    public List<Track> getAllTracksNotInPlaylist(int playlistId) {
        List<Track> tracks = new ArrayList<>();
        try {
            tracks.addAll(songDAO.getAllSongsNotInPlaylist(playlistId));
            tracks.addAll(videoDAO.getAllVideosNotInPlaylist(playlistId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tracks;
    }
}
