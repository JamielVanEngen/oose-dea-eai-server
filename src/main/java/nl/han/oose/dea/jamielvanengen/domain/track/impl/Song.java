package nl.han.oose.dea.jamielvanengen.domain.track.impl;

import nl.han.oose.dea.jamielvanengen.domain.track.Track;

public class Song extends Track {
    private String album;

    public Song(int id, String performer, String titel, String url, int afspeelduur, Integer playcount, String album) {
        super(id, performer, titel, url, afspeelduur, playcount);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }
}
