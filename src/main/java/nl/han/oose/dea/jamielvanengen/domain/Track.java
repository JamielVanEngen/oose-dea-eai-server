package nl.han.oose.dea.jamielvanengen.domain;

import java.time.LocalDate;

public class Track {
    private int tackid;
    private String performer, titel, url, album, beschrijving;
    private LocalDate publicatiedatum;
    private int afspeelduur;
    private Integer playcount;

    public Track(int tackid, String performer, String titel, String url, String album, String beschrijving, LocalDate publicatiedatum, int afspeelduur, Integer playcount) {
        this.tackid = tackid;
        this.performer = performer;
        this.titel = titel;
        this.url = url;
        this.album = album;
        this.beschrijving = beschrijving;
        this.publicatiedatum = publicatiedatum;
        this.afspeelduur = afspeelduur;
        this.playcount = playcount;
    }

    public int getTackid() {
        return tackid;
    }

    public void setTackid(int tackid) {
        this.tackid = tackid;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAfspeelduur() {
        return afspeelduur;
    }

    public void setAfspeelduur(int afspeelduur) {
        this.afspeelduur = afspeelduur;
    }

    public Integer getPlaycount() {
        return playcount;
    }

    public String getAlbum() {
        return album;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public LocalDate getPublicatiedatum() {
        return publicatiedatum;
    }
}
