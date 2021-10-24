package br.com.amaro.login.domain.service;

import br.com.amaro.login.domain.adapter.LoginRepository;
import br.com.amaro.login.domain.exception.CreateLoginException;
import br.com.amaro.login.domain.exception.CreateSessionException;
import br.com.amaro.login.domain.exception.UpdateLoginExeption;
import br.com.amaro.login.domain.port.LoginPort;
import br.com.amaro.login.integration.entity.LoginEntity;
import br.com.amaro.login.rest.model.Login;
import br.com.amaro.login.rest.model.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService implements LoginPort {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Login createLogin(Login login) throws CreateLoginException {
        if(loginRepository.existsByEmail(login.getEmail())){
            throw new CreateLoginException();
        }
        login.setToken(UUID.randomUUID().toString());
        return LoginMapper.entitytoModel(loginRepository.save(LoginMapper.modelToEntity(login)));
    }

    @Override
    public Login getLogin(UUID idLogin) {
        return LoginMapper.entitytoModel(loginRepository.findByIdLogin(idLogin));
    }

    @Override
    public Login updateLogin(Login login) throws UpdateLoginExeption {
        if(loginRepository.existsByIdLogin(login.getIdLogin())) {
            return LoginMapper.entitytoModel(loginRepository.save(LoginMapper.modelToEntity(login)));
        } else {
            throw new UpdateLoginExeption();
        }
    }

    @Override
    public boolean deleteLogin(Login login) {
        try{
            loginRepository.delete(loginRepository.findByIdLogin(login.getIdLogin()));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Login createSession(Login login) throws CreateSessionException {
        LoginEntity entity = loginRepository.findByEmailAndSenha(login.getEmail(), login.getPassword());
        if(entity != null){
            entity.setToken(UUID.randomUUID().toString());
            return LoginMapper.entitytoModel(loginRepository.save(entity));
        } else {
            throw new CreateSessionException();
        }
    }

    @Override
    public boolean getSession(UUID idLogin, String token) {
        return loginRepository.existsByIdLoginAndToken(idLogin,token);
    }

}
