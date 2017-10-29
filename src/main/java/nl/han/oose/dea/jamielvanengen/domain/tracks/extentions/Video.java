package nl.han.oose.dea.jamielvanengen.domain.tracks.extentions;

import nl.han.oose.dea.jamielvanengen.domain.tracks.Track;

import java.time.LocalDate;

public class Video extends Track {
    private LocalDate publicatiedatum;

    public LocalDate getPublicatiedatum() {
        return publicatiedatum;
    }

    public void setPublicatiedatum(LocalDate publicatiedatum) {
        this.publicatiedatum = publicatiedatum;
    }
}
