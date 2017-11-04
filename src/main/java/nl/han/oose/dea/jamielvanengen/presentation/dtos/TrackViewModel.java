package nl.han.oose.dea.jamielvanengen.presentation.dtos;

import java.time.LocalDate;

public class TrackViewModel {
    private int id;
    private String title;
    private String performer;
    private int duration;
    private String album;
    private Integer playcount;
    private String publicationDate;
    private String description;

    public TrackViewModel() {
    }

    public TrackViewModel(int id, String title, String performer, int duration, String album, Integer playcount, String publicationDate, String description) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.album = album;
        this.playcount = playcount;
        this.publicationDate = publicationDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getPlaycount() {
        return playcount;
    }

    public void setPlaycount(Integer playcount) {
        this.playcount = playcount;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
