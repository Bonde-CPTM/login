package br.com.amaro.login.integration.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "login")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginEntity {

    @Id
    @NotNull
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID idLogin;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "senha")
    private String senha;

    @NotNull
    @Column(name = "token")
    private String token;
}
