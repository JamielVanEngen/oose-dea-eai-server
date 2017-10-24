package nl.han.oose.dea.jamielvanengen.presentation.dtos;

public class AuthorizedUser {
    private String password;
    private String user;

    public AuthorizedUser() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
