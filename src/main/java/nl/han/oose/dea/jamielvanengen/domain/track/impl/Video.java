package nl.han.oose.dea.jamielvanengen.domain.track.impl;

import nl.han.oose.dea.jamielvanengen.domain.track.Track;

import java.sql.Date;

public class Video extends Track {
    private String beschrijving;
    private Date publicatiedatum;

    public Video(int tackid, String performer, String titel, String url, int afspeelduur, Integer playcount, String beschrijving, Date publicatiedatum) {
        super(tackid, performer, titel, url, afspeelduur, playcount);
        this.beschrijving = beschrijving;
        this.publicatiedatum = publicatiedatum;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public Date getPublicatiedatum() {
        return publicatiedatum;
    }
}
