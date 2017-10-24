package nl.han.oose.dea.jamielvanengen.presentation.dtos;

public class LoginRequest {
    private String user;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.user = username;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
