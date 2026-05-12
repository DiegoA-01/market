package com.proyect.products.config;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import java.util.function.Function;

import com.proyect.products.entity.Rol;
import com.proyect.products.entity.Users;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



import org.springframework.beans.factory.annotation.Value;
import lombok.extern.log4j.Log4j2;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;



@Service
@Log4j2
public class JwtService {


    @Value("${security.jwt.secret-key}")
    private String secretKey ;


    @Value("${security.jwt.token-expiration}")
    private long expirationTime ;


    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    /*public String generateToken(Long userId, String rolId, String email){
        return Jwts.builder()
                .subject(email)
                .claims(Map.of("userId", userId, "rolId", rolId))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSecretKey())
                .compact();
    }*/

        public String generateToken( Users user){
        return Jwts.builder()
                .claims(Map.of(
                "userId", user.getUserId(),
                "rolId", user.getRol().name() 
            ))
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSecretKey())
                .compact();
    }



    public Boolean validateToken(String token){
        try {
            Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            log.error("Token inválido:" + e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Error al validar el token:" + e.getMessage());
            return false;
        }
    }


    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    public String extractEmail(String token){
        return  extractClaims(token, Claims ::getSubject);
    }

    public Long extractUserId(String token){
        return extractClaims(token, claims -> claims.get("userId", Long.class) );
    }

    public String extractRolId(String token){
        return extractClaims(token, claims -> claims.get("rolId", String.class));
    }


    public String refreshToken(String token ) throws Exception{
        Claims claims;

        try{
            claims = Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch(ExpiredJwtException e){
            throw new Exception("token is expired"  + e.getMessage() );
        }catch(JwtException e){
            throw new Exception("Token is invalid" + e.getMessage());
        }catch(Exception e){
            throw new Exception("Server error" + e.getMessage());
        }

        Users user = new Users();
            user.setUserId(claims.get("userId", Long.class));
            user.setEmail(claims.getSubject());
            user.setRol(Rol.valueOf(claims.get("rolId", String.class)));

return generateToken(user);


        //return generateToken(claims.get("userId", Long.class),  claims.get("rolId" , String.class),claims.getSubject());

    }
}
