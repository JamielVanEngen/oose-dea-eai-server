package nl.han.oose.dea.jamielvanengen.services;

import nl.han.oose.dea.jamielvanengen.datasources.daos.implementation.UserDAO;
import nl.han.oose.dea.jamielvanengen.domain.User;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.sasl.AuthenticationException;

@Default
public class LoginService {
    @Inject
    private UserDAO userDao;

    @Inject
    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public User login(String username, String password) throws AuthenticationException {
        User user = userDao.getUserByUsername(username);
        if (user.authenticate(password)) {
            return user;
        }
        throw new AuthenticationException();
    }
}
