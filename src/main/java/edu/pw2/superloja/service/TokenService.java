package edu.pw2.superloja.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.pw2.superloja.model.usuario.Usuario;
import edu.pw2.superloja.model.usuario.UsuarioDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(UsuarioDetails usuario){
        try{
            Algorithm hmac256 = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("superloja-auth-api")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(genExpirantionInstant())
                    .sign(hmac256);
            return token;
        }catch (JWTCreationException e){
            throw new RuntimeException("Error while genetating token", e);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm hmac256 = Algorithm.HMAC256(secret);
            String username =JWT.require(hmac256)
                    .withIssuer("superloja-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
            return username;
        }catch (JWTVerificationException e){
            return "";
        }
    }


    private Instant genExpirantionInstant(){
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
