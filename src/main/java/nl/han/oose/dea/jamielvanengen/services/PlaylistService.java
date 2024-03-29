package nl.han.oose.dea.jamielvanengen.services;

import nl.han.oose.dea.jamielvanengen.datasources.daos.implementation.PlaylistDAO;
import nl.han.oose.dea.jamielvanengen.domain.Playlist;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistService {
    @Inject
    PlaylistDAO playlistDAO;

    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        try {
            playlists = playlistDAO.getAllPlaylists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlists;
    }

    public void deletePlaylistByid(int id) {
        try {
            playlistDAO.deletePlaylistById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Playlist getPlaylistById(int id) {
        Playlist playlist = new Playlist();
        try {
            playlist = playlistDAO.getPlaylistById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlist;
    }

    public void addPlaylist(String name, int userId) {
        try {
            playlistDAO.addPlaylist(name, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editPlaylist(int id, String name) {
        try {
            playlistDAO.editPlaylist(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeTrackFromPlaylist(int playlistId, int trackId) {
        try {
            playlistDAO.removeTrackFromPlaylist(playlistId, trackId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTracktoPlaylist(int playlistId, int trackId, boolean isAvailableOffline) {
        try {
            playlistDAO.addTrackToPlaylist(playlistId, trackId, isAvailableOffline);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
