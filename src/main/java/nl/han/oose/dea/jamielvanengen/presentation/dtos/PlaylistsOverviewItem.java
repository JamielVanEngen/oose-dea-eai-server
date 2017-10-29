package nl.han.oose.dea.jamielvanengen.presentation.dtos;

import nl.han.oose.dea.jamielvanengen.domain.tracks.Track;

import java.util.List;

public class PlaylistsOverviewItem {
    private int id;
    private String name;
    private boolean owner;
    private List<Track> tracks;

    public PlaylistsOverviewItem(int id, String name, boolean owner, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
