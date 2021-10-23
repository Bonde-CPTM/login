package br.com.amaro.login.domain.adapter;

import br.com.amaro.login.integration.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginRepository extends JpaRepository<LoginEntity, String> {
    LoginEntity findByEmailAndSenha(String email, String senha);
    LoginEntity findByIdLogin(UUID idLogin);
    boolean existsByEmailAndSenha(String email, String senha);
    boolean existsByIdLogin(UUID idLogin);
}
