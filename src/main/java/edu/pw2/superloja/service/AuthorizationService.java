package edu.pw2.superloja.service;

import edu.pw2.superloja.model.usuario.Usuario;
import edu.pw2.superloja.model.usuario.UsuarioDetails;
import edu.pw2.superloja.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = usuarioRepo.findByUsername(username);
        UsuarioDetails ud = new UsuarioDetails(u);
        return ud;
    }
}
