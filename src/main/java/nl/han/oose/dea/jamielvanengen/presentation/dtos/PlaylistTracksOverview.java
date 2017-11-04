package nl.han.oose.dea.jamielvanengen.presentation.dtos;

import java.util.List;

public class PlaylistTracksOverview {
    private List<PlaylistTrack> tracks;

    public PlaylistTracksOverview(List<PlaylistTrack> tracks) {
        this.tracks = tracks;
    }

    public List<PlaylistTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<PlaylistTrack> tracks) {
        this.tracks = tracks;
    }
}
