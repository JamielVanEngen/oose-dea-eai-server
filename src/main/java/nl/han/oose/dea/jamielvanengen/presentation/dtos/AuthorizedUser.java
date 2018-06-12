package nl.han.oose.dea.jamielvanengen.presentation.dtos;

public class AuthorizedUser {
    private String token;
    private String user;

    public AuthorizedUser(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user;
    }
}
