package edu.pw2.superloja.model.usuario;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UsuarioRole role;

    public Usuario(String username, String password, UsuarioRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
