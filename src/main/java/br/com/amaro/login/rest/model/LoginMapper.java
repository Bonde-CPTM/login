package br.com.amaro.login.rest.model;

import br.com.amaro.login.integration.entity.LoginEntity;

public class LoginMapper {
    public static Login entitytoModel(LoginEntity entity){
        return Login.builder()
                .idLogin(entity.getIdLogin())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .password(entity.getSenha())
                .token(entity.getToken())
                .build();
    }
    public static LoginEntity modelToEntity(Login model){
        return LoginEntity.builder()
                .idLogin(model.getIdLogin())
                .nome(model.getNome())
                .email(model.getEmail())
                .senha(model.getPassword())
                .token(model.getToken())
                .build();
    }
}
