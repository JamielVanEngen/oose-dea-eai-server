package nl.han.oose.dea.jamielvanengen.presentation.controllers;

import nl.han.oose.dea.jamielvanengen.constants.HttpResponse;
import nl.han.oose.dea.jamielvanengen.domain.Token;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.AuthorizedUser;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.LoginRequest;
import nl.han.oose.dea.jamielvanengen.domain.User;
import nl.han.oose.dea.jamielvanengen.services.LoginService;
import nl.han.oose.dea.jamielvanengen.services.TokenService;

import javax.inject.Inject;
import javax.security.sasl.AuthenticationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @Inject
    private LoginService loginService;

    @Inject
    private TokenService tokenService;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest form) {
        try {
            User user = loginService.login(form.getUser(), form.getPassword());
            Token token = tokenService.setToken(user.getId());
            AuthorizedUser authorizedUser = new AuthorizedUser(token.getUuid(), user.getUser());
            return Response.status(HttpResponse.OK.getValue()).entity(authorizedUser).build();
        } catch (AuthenticationException e) {
            return Response.status(HttpResponse.UNAUTHORIZED.getValue()).build();
        }
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
