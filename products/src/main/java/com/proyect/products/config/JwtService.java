/*package com.proyect.products.config;

import java.security.Key;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.io.Decoder;
import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class JwtService {
    
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSigniKey(){
        byte[] keyBytes = Decoder.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
    */
