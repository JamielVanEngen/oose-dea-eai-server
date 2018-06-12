package nl.han.oose.dea.jamielvanengen.domain.track.impl;

import nl.han.oose.dea.jamielvanengen.domain.track.Track;

import java.sql.Date;
import java.time.LocalDate;

public class Video extends Track {
    private String beschrijving;
    private LocalDate publicatiedatum;

    public Video(int id, String performer, String titel, String url, int afspeelduur, Integer playcount, String beschrijving, LocalDate publicatiedatum) {
        super(id, performer, titel, url, afspeelduur, playcount);
        this.beschrijving = beschrijving;
        this.publicatiedatum = publicatiedatum;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public LocalDate getPublicatiedatum() {
        return publicatiedatum;
    }
}
