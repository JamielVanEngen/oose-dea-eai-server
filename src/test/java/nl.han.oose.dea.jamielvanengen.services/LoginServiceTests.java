package nl.han.oose.dea.jamielvanengen.services;

import nl.han.oose.dea.jamielvanengen.datasources.daos.implementation.UserDAO;
import nl.han.oose.dea.jamielvanengen.domain.User;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.security.sasl.AuthenticationException;


public class LoginServiceTests {
    @Mock
    private UserDAO userDAO;

    private LoginService loginService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setup() {
        loginService = new LoginService();
    }

    @Test
    public void testIfLoginReturnsUserIfAuthenticated() throws AuthenticationException {
        String password = Mockito.anyString();
        User user = new User(1, "", password);
        Mockito.when(userDAO.getUserByUsername(Mockito.anyString())).thenReturn(user);
        User result = loginService.login(user.getUser(), password);
        Assert.assertEquals(user, result);
    }
}
