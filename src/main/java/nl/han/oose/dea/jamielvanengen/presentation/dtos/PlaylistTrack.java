package nl.han.oose.dea.jamielvanengen.presentation.dtos;

import java.sql.Date;
import java.time.LocalDate;

public class PlaylistTrack extends TrackViewModel {
    private boolean offlineAvailable;

    public PlaylistTrack() {
    }

    public PlaylistTrack(int id, String title, String performer, int duration, String album, Integer playcount, String publicationDate, String description, boolean offlineAvailable) {
        super(id, title, performer, duration, album, playcount, publicationDate, description);
        this.offlineAvailable = offlineAvailable;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}
