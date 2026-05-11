package com.proyect.products.config;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.proyect.products.entity.Users;
import com.proyect.products.enums.Rol;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class JwtService {
   

    /**
     * llave secreta para generar firma
     */
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    /**
     * Tiempo de expiracion de token en millisegundos 
     */
    @Value("${security.jwt.expiration}")
    private Long expiration;

    /**
     * Genera la firma secreta apartir del secretKey en el yaml
     * 
     * @return firma secreta para firmar el jwt
     */
    private SecretKey getSigninKey(){
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }


    /**
     * Generar un token de Jwt
     * 
     * @param users
     * @return
     */
    public String generatedToken(Users users){
        Date now = new Date();
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        
        return Jwts.builder()
                .claims(Map.of("userId", users.getUserId(), "rol", users.getRol()))
                .subject(users.getEmail())
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(getSigninKey())
                .compact();
                
    }


    /**
     * Metodo de validacion del token y que si es valido o ya expiro el token
     * 
     * @param token
     * @return
     */
    public Boolean validToken(String token){
        try {
            Jwts.parser().verifyWith(getSigninKey()).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            log.error("token invalido" + e.getMessage());
            return false;
        }catch(Exception e){
            log.error("Error al validar el token" + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo de extraer todo los claims payload 
     * 
     * @param <T>
     * @param token
     * @param resolver
     * @return
     */
    public <T> T extractClaims(String token, Function <Claims,T> resolver){
        final Claims claims = Jwts.parser().verifyWith(getSigninKey()).build().parseSignedClaims(token).getPayload();
        return resolver.apply(claims);
    }

    /**
     * Extraer el propietario del token
     * 
     * @param token
     * @return
     */
    public String extractUserName(String token){
        return extractClaims(token, Claims::getSubject);
    }

    /**
     * Metodo extraer el ID del usuario
     * 
     * @param token
     * @return
     */
    public Long extractUserId(String token){
        return extractClaims(token, claims->claims.get("userId", Long.class));
    }


    /**
     * Metodo de estraer el rol 
     * 
     * @param token
     * @return
     */
    public String extractRol(String token){
        return extractClaims(token, claims -> claims.get("rol", String.class));
    }

    /**
     * Metodo de refrescar el token da uno nuevo sobre el token original
     * 
     * @param token
     * @return
     * @throws Exception
     */
    public String refreshToken(String token)throws Exception{
        Claims claims;
        try {
            claims = Jwts.parser().verifyWith(getSigninKey()).build().parseSignedClaims(token).getPayload();
        } catch (ExpiredJwtException e) {
            throw new Exception("Token ha expirado" +e.getMessage());
        }catch(JwtException e){
            throw new Exception("El token no es valido" + e.getMessage());
        }catch(Exception e){
            throw new Exception("Un error en servicio" + e.getMessage());
        }
        Users users = new Users();

        users.setUserId(claims.get("userId", Long.class));
        users.setEmail(claims.getSubject());
        users.setRol(Rol.valueOf(claims.get("rol", String.class)));

        return generatedToken(users);
    }
    
}
    
