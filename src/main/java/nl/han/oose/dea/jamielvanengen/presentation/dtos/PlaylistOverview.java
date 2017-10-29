package nl.han.oose.dea.jamielvanengen.presentation.dtos;

import java.util.List;

public class PlaylistOverview {
    private List<PlaylistOverviewItem> playlists;
    private int length;

    public PlaylistOverview(List<PlaylistOverviewItem> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<PlaylistOverviewItem> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }
}
