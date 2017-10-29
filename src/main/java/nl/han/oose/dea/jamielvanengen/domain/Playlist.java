package nl.han.oose.dea.jamielvanengen.domain;

public class Playlist {
    private int id;
    private String name;
    private int userid;

    public Playlist(int id, String name, int userid) {
        this.id = id;
        this.name = name;
        this.userid = userid;
    }

    public Playlist() {

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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
