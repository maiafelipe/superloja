package edu.pw2.superloja.controller;

import edu.pw2.superloja.model.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private ApplicationContext context;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioAuthDTO data){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        Authentication auth = authManager.authenticate(token);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsuarioRegisterDTO data){
        if(usuarioRepo.findByUsername(data.username()) != null){
            return ResponseEntity.badRequest().build();
        }
        PasswordEncoder pe = context.getBean(PasswordEncoder.class);
        String digest = pe.encode(data.password());
        Usuario u = new Usuario(data.username(), digest, data.role());
        usuarioRepo.save(u);
        return ResponseEntity.ok().build();
    }
}
