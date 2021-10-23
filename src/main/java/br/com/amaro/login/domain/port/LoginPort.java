package br.com.amaro.login.domain.port;

import br.com.amaro.login.domain.exception.CreateLoginException;
import br.com.amaro.login.domain.exception.CreateSessionException;
import br.com.amaro.login.domain.exception.UpdateLoginExeption;
import br.com.amaro.login.rest.model.Login;

import java.util.Optional;

public interface LoginPort {
    Login createLogin(Login login) throws CreateLoginException;

    Login getLogin(Login login);

    Login updateLogin(Login login) throws UpdateLoginExeption;

    boolean deleteLogin(Login login);

    Login createSession(Login login) throws CreateSessionException;

    boolean getSession(Login login);
}