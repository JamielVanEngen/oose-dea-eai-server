package nl.han.oose.dea.jamielvanengen.services;

import nl.han.oose.dea.jamielvanengen.datasources.daos.implementation.UserDAO;
import nl.han.oose.dea.jamielvanengen.domain.User;
import nl.han.oose.dea.jamielvanengen.presentation.controllers.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.security.sasl.AuthenticationException;

public class LoginServiceTest {
    LoginService loginService;
    private UserDAO userDAO;

    @Before
    public void setup() {
        loginService = new LoginService();
        userDAO = Mockito.mock(UserDAO.class);
        loginService.setUserDao(userDAO);
    }

    @Test
    public void test_doLoginCallsForUser() throws AuthenticationException {
        int id = 1;
        String username = "username";
        String password = "test";
        User expected = new User(1, username, password);

        Mockito.when(userDAO.getUserByUsername(Mockito.anyString())).thenReturn(expected);
        loginService.login(username, password);
        Mockito.verify(userDAO).getUserByUsername(username);
    }
}
