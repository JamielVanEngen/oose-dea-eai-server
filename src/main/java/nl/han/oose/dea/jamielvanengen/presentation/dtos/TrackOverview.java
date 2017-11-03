package nl.han.oose.dea.jamielvanengen.presentation.dtos;

import java.util.List;

public class TrackOverview {
    private List<TrackViewModel> tracks;

    public TrackOverview(List<TrackViewModel> tracks) {
        this.tracks = tracks;
    }

    public List<TrackViewModel> getTracks() {
        return tracks;
    }
}
