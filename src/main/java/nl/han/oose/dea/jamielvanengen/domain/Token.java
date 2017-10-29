package nl.han.oose.dea.jamielvanengen.domain;

import java.time.LocalDate;

public class Token {
    private String uuid;
    private int userId;
    private LocalDate date;

    public Token() {
    }

    public Token(String uuid, int userId, LocalDate date) {
        this.uuid = uuid;
        this.userId = userId;
        this.date = date;
    }

    public String getUuid() {
        return uuid;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }
}
