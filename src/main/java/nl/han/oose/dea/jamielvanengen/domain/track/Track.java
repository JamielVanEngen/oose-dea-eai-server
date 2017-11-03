package nl.han.oose.dea.jamielvanengen.domain.track;

import java.time.LocalDate;

public abstract class Track {
    private int tackid;
    private String performer, titel, url;
    private int afspeelduur;
    private Integer playcount;

    public Track(int tackid, String performer, String titel, String url, int afspeelduur, Integer playcount) {
        this.tackid = tackid;
        this.performer = performer;
        this.titel = titel;
        this.url = url;
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
}
