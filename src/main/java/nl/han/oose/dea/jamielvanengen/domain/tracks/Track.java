package nl.han.oose.dea.jamielvanengen.domain.tracks;

public abstract class Track {
    private int tackid;
    private String performer, titel, url;
    private int afspeelduur;

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
}
