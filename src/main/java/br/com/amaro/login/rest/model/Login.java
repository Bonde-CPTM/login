package br.com.amaro.login.rest.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Login {
    private UUID idLogin;
    private String email;
    private String password;
    private String token;

}
