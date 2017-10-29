package nl.han.oose.dea.jamielvanengen.domain.tracks.extentions;

import nl.han.oose.dea.jamielvanengen.domain.tracks.Track;

public class Song extends Track {
    private String album;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
