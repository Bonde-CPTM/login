package br.com.amaro.login.rest;

import br.com.amaro.login.domain.exception.CreateSessionException;
import br.com.amaro.login.domain.exception.UpdateLoginExeption;
import br.com.amaro.login.domain.port.LoginPort;
import br.com.amaro.login.rest.model.Data;
import br.com.amaro.login.rest.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginPort loginPort;

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<?> createLogin(@RequestBody Login login){

        if(checkLogin(login)){
            return ResponseEntity.status(402).body(Data.builder()
                    .conteudo("Email ou senha inválido")
                    .build());
        }

        try {

            return ResponseEntity.status(201).body(Data.builder()
                    .conteudo(loginPort.createLogin(login))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Data.builder()
                    .conteudo("Não foi possível salvar o usuário!")
                    .build());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping()
    public ResponseEntity<?> getLogin (@RequestBody Login login){
        if(checkLogin(login)){
            return ResponseEntity.status(402).body(Data.builder()
                    .conteudo("Email ou senha inválido")
                    .build());
        }
        try {

            return ResponseEntity.ok().body(Data.builder()
                    .conteudo(loginPort.getLogin(login))
                    .build());
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Não foi possível consultar login");
        }
    }

    @CrossOrigin(origins = "*")
    @PatchMapping()
    public ResponseEntity<?> updateLogin(@RequestBody Login login){
        if(checkLogin(login)){
            return ResponseEntity.status(402).body(Data.builder()
                    .conteudo("Email ou senha inválido")
                    .build());
        }
        try {

            return ResponseEntity.ok().body(Data.builder()
                    .conteudo(loginPort.updateLogin(login))
                    .build());
        } catch (UpdateLoginExeption e) {
            return ResponseEntity.internalServerError().body(Data.builder()
                    .conteudo("Não foi possível atualizar o usuário!")
                    .build());
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/sessao")
    public ResponseEntity<?> createSession(@RequestBody Login login){
        if(checkLogin(login)){
            return ResponseEntity.status(402).body(Data.builder()
                    .conteudo("Email ou senha inválido")
                    .build());
        }
        try {

            return ResponseEntity.status(201).body(Data.builder()
                    .conteudo(loginPort.createSession(login))
                    .build());
        } catch (CreateSessionException e) {
            return ResponseEntity.internalServerError().body(Data.builder()
                    .conteudo("Não foi possível criar sessão para o usuário!")
                    .build());
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping()
    public ResponseEntity<?> deleteLogin(@RequestBody Login login){
        if(checkLogin(login)){
            return ResponseEntity.status(402).body(Data.builder()
                    .conteudo("Email ou senha inválido")
                    .build());
        }
        try {

            return loginPort.deleteLogin(login)?
                    ResponseEntity.status(200).body(Data.builder()
                    .conteudo("Usuário deletado com sucesso")
                    .build()):
                    ResponseEntity.internalServerError().body(Data.builder()
                    .conteudo("Não foi possível deletar o usuário!")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Data.builder()
                    .conteudo("Não foi possível deletar o usuário!")
                    .build());
        }
    }

    public boolean checkLogin(Login login){
        return login.getEmail().isEmpty() || login.getPassword().isEmpty();
    }
}
