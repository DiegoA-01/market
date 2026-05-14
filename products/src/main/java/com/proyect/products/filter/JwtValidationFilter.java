package com.proyect.products.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.proyect.products.config.JwtService;

import java.io.IOException;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtValidationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;


    @Override

    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{

        String autHeader = request.getHeader("Authorization");

        if(autHeader == null || !autHeader.startsWith("Bearer ")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Header is missing in the request\"}");
            return;
        }

        String token = autHeader.replace("Bearer ", "");

        try {
            
            if(jwtService.validToken(token)){
                String email = jwtService.extractEmail(token);
                String rol = jwtService.extractRol(token);
                Long userId = jwtService.extractUserId(token);
                
                System.out.println("claims extraidos " + email + " roll " + rol + " userId " + userId);

                request.setAttribute("email", email);
                request.setAttribute("rol", rol);
                request.setAttribute("userId", userId);
                
                filterChain.doFilter(request, response);

            }else{
                System.out.println("Token denegado en validToken(token)");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Invalid token or Expired\"}");
            }
        }catch (io.jsonwebtoken.JwtException | IllegalArgumentException e) {
            System.out.println("Error parseando el token: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Validation failed\"}");
        }

    }
}

