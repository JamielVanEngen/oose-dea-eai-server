package nl.han.oose.dea.jamielvanengen.domain;

import java.time.LocalDate;

public class Token {
    private String uuid;
    private int id;
    private LocalDate date;

    public Token() {
    }

    public Token(String uuid, int id, LocalDate date) {
        this.uuid = uuid;
        this.id = id;
        this.date = date;
    }

    public String getUuid() {
        return uuid;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }
}
