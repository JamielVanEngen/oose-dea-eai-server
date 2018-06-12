package nl.han.oose.dea.jamielvanengen.domain.track;

public class TrackPerPlaylist {
    private Track track;
    private boolean offlineAvailable;

    public TrackPerPlaylist() {
        track = new Track();
    }

    public TrackPerPlaylist(Track track, boolean offlineAvailable) {
        this.track = track;
        this.offlineAvailable = offlineAvailable;
    }

    public Track getTrack() {
        return track;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(boolean availableOffline) {
        offlineAvailable = availableOffline;
    }

    public void setId(int id) {
        track.setId(id);
    }
}
