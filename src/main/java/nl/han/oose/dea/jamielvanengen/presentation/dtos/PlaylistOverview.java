package nl.han.oose.dea.jamielvanengen.presentation.dtos;

import java.util.List;

public class PlaylistOverview {
    private List<PlaylistsOverviewItem> playlists;
    private int length;

    public PlaylistOverview(List<PlaylistsOverviewItem> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<PlaylistsOverviewItem> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }
}
