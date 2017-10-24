package nl.han.oose.dea.jamielvanengen.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String user;
    private String password;

    public User() {
    }

    public User(String user, String password, String token) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
