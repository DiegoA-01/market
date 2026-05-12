package com.proyect.products.config;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

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
  public String generatedToken(Users users) {
    Date now = new Date();
    Date expirationDate = new Date(System.currentTimeMillis() + expiration);

    return Jwts.builder()
            .claims(Map.of("userId", users.getUserId(), "role", users.getRole()))
            .subject(users.getEmail())
            .issueAt(now)
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
      Jwts.parser().verifyWith(getSigninkey()).build().parseSignedClaims(toke);}
      return true;
    } catch (JwtExeption e) {
        log.error("Invalid token " + e.getMessage());
        return false;
    } catch (Exeption e) {
        log.error("Error to validate token " + e.getMessage());
        return false;
    }
}
