package br.com.amaro.login.rest.model;

import br.com.amaro.login.integration.entity.LoginEntity;

public class LoginMapper {
    public static Login entitytoModel(LoginEntity entity){
        return Login.builder()
                .idLogin(entity.getIdLogin())
                .email(entity.getEmail())
                .senha(entity.getSenha())
                .token(entity.getToken())
                .build();
    }
    public static LoginEntity modelToEntity(Login model){
        return LoginEntity.builder()
                .idLogin(model.getIdLogin())
                .email(model.getEmail())
                .senha(model.getSenha())
                .token(model.getToken())
                .build();
    }
}