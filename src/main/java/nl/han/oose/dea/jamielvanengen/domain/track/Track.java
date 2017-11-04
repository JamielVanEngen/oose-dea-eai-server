package nl.han.oose.dea.jamielvanengen.domain.track;

public abstract class Track {
    private int id;
    private String performer, titel, url;
    private int afspeelduur;
    private Integer playcount;

    public Track(int id, String performer, String titel, String url, int afspeelduur, Integer playcount) {
        this.id = id;
        this.performer = performer;
        this.titel = titel;
        this.url = url;
        this.afspeelduur = afspeelduur;
        this.playcount = playcount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
