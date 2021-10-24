package br.com.amaro.login.rest;

import br.com.amaro.login.domain.exception.CreateLoginException;
import br.com.amaro.login.domain.exception.CreateSessionException;
import br.com.amaro.login.domain.exception.UpdateLoginExeption;
import br.com.amaro.login.domain.port.LoginPort;
import br.com.amaro.login.rest.model.Data;
import br.com.amaro.login.rest.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginPort loginPort;

    @CrossOrigin(origins = "*")
    @PostMapping()
    public ResponseEntity<?> createLogin(@RequestBody Login login){

        if(checkLogin(login)){
            return ResponseEntity.status(406).body(Data.builder()
                    .content("Email ou senha inválido")
                    .build());
        }

        try {

            return ResponseEntity.status(201).body(Data.builder()
                    .content(loginPort.createLogin(login))
                    .build());
        } catch (CreateLoginException e) {
            return ResponseEntity.status(406).body(Data.builder()
                    .content("Usuário cadastrado!")
                    .build());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{idLogin}")
    public ResponseEntity<?> getLogin (@PathParam(value = "idLogin") UUID idLogin){
        if(idLogin.toString().isEmpty()){
            return ResponseEntity.status(406).body(Data.builder()
                    .content("Email ou senha inválido")
                    .build());
        }
        try {

            return ResponseEntity.ok().body(Data.builder()
                    .content(loginPort.getLogin(idLogin))
                    .build());
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Não foi possível consultar login");
        }
    }

    @CrossOrigin(origins = "*")
    @PatchMapping()
    public ResponseEntity<?> updateLogin(@RequestBody Login login){
        if(checkLogin(login)){
            return ResponseEntity.status(406).body(Data.builder()
                    .content("Email ou senha inválido")
                    .build());
        }
        try {

            return ResponseEntity.ok().body(Data.builder()
                    .content(loginPort.updateLogin(login))
                    .build());
        } catch (UpdateLoginExeption e) {
            return ResponseEntity.status(406).body(Data.builder()
                    .content("Não foi possível atualizar o usuário!")
                    .build());
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/signin")
    public ResponseEntity<?> createSession(@RequestBody Login login){
        if(checkLogin(login)){
            return ResponseEntity.status(406).body(Data.builder()
                    .content("Email ou senha inválido")
                    .build());
        }
        try {

            return ResponseEntity.status(201).body(Data.builder()
                    .content(loginPort.createSession(login))
                    .build());
        } catch (CreateSessionException e) {
            return ResponseEntity.status(406).body(Data.builder()
                    .content("Não foi possível criar sessão para o usuário!")
                    .build());
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping()
    public ResponseEntity<?> deleteLogin(@RequestBody Login login){
        if(checkLogin(login)){
            return ResponseEntity.status(406).body(Data.builder()
                    .content("Email ou senha inválido")
                    .build());
        }
        try {

            return loginPort.deleteLogin(login)?
                    ResponseEntity.status(200).body(Data.builder()
                    .content("Usuário deletado com sucesso")
                    .build()):
                    ResponseEntity.internalServerError().body(Data.builder()
                    .content("Não foi possível deletar o usuário!")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(406).body(Data.builder()
                    .content("Não foi possível deletar o usuário!")
                    .build());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("{idLogin}/sessao/{token}")
    public ResponseEntity<?> getSession(@PathParam(value = "idLogin") UUID idLogin,
                                        @PathParam(value = "token") String token){
        return loginPort.getSession(idLogin,token)?
                ResponseEntity.ok().body("Sessão válida!"):
                ResponseEntity.status(402).body("Sessão inválida!");
    }

    public boolean checkLogin(Login login){
        try {
            return login.getEmail().isEmpty() || login.getPassword().isEmpty();
        } catch (NullPointerException e){
            return false;
        }
    }
}
