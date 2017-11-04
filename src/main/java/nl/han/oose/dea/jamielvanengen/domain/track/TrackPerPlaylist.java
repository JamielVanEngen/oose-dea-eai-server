package nl.han.oose.dea.jamielvanengen.domain.track;

public class TrackPerPlaylist {
    private Track track;
    private boolean isAvailableOffline;

    public TrackPerPlaylist(Track track, boolean isAvailableOffline) {
        this.track = track;
        this.isAvailableOffline = isAvailableOffline;
    }

    public Track getTrack() {
        return track;
    }

    public boolean isAvailableOffline() {
        return isAvailableOffline;
    }
}
