package com.proyect.products.config;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.proyect.products.entity.UserEntity;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class JwtService {
    
    /**
   * Secret key to generate the sign.
   */
@org.springframework.beans.factory.annotation.Value("${security.jwt.secret-key}")
private String secretKey;

    /**
   * Expiration time for token in milliseconds.
   */
@org.springframework.beans.factory.annotation.Value("${security.jwt.expiration}")
private Long expiration;

    /**
   * Generates the secret key from secretKey in yaml.
   * 
   * @return Secret sign to sign jwt.
   */
private SecretKey getSigninkey(){
    byte[] keyByte = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyByte);
}

    /**
   * generates token jwt.
   * 
   * @param users
   * @return
   */
public String generatedToken(UserEntity user) {
    Date now = new Date();
    Date expirationDate = new Date(System.currentTimeMillis() + expiration);

    return Jwts.builder()
            .claims(Map.of("userId", user.getUserId(), "role", user.getRole()))
            .subject(user.getEmail())
            .issuedAt(now)
            .expiration(expirationDate)
            .signWith(getSigninkey())
            .compact();
}

    /**
   * Method to validate token to know if valid or has expired.
   * 
   * @param token
   * @return
   */
public Boolean validToken(String token) {
    try {
    Jwts.parser().verifyWith(getSigninkey()).build().parseSignedClaims(token);
    return true;
    } catch (JwtException e) {
        log.error("Invalid token " + e.getMessage());
        return false;
    } catch (Exception e) {
        log.error("Error to validate token " + e.getMessage());
        return false;
    }
}

/**
 * Method to obtain all the payload claims
 * 
 * @param <T>
 * @param token
 * @param resolver
 * @return
 */
public <T> T extraClaims(String token, Function <Claims,T> resolver) {
    final Claims claims = Jwts.parser().verifyWith(getSigninkey()).build().parseSignedClaims(token).getPayload();
    return resolver.apply(claims);
}

/**
 * Methos to obtain the owner from token.
 * 
 * @param token
 * @return
 */
public String obtainUserName(String token) {
    return obtainClaims(token, Claims::getSubject);
}

/**
 * Method to obtain Id from user;
 * 
 * @param token
 * @return
 */
public Long obtainUserId(String token) {
    return obtainClaims(token, Claims->claims.get("userID", Long.class));
}
}