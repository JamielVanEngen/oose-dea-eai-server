package nl.han.oose.dea.jamielvanengen.presentation.controllers;

import nl.han.oose.dea.jamielvanengen.constants.HttpResponse;
import nl.han.oose.dea.jamielvanengen.presentation.dtos.LoginRequest;
import nl.han.oose.dea.jamielvanengen.domain.User;
import nl.han.oose.dea.jamielvanengen.services.LoginService;

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
    private LoginService service;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest form) {
        try {
            User user = service.login(form.getUser(), form.getPassword());
            return Response.status(HttpResponse.OK.getValue()).entity(user).build();
        } catch (AuthenticationException e) {
            return Response.status(HttpResponse.UNAUTHORIZED.getValue()).build();
        }
    }
}
