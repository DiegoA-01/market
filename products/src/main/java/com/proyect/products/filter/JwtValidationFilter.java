package com.proyect.products.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
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

    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException{

        String autHeader = request.getHeader("Autorization");

        if(autHeader == null || !autHeader.startsWith("Bearer")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Header is missing in the request\"}");
            return;
        }

        String token = autHeader.replace("Bearer ", "");

        try {

            if(jwtService.validateToken(token)){
                String email = jwtService.extractEmail(token);
                String rolId = jwtService.extractRolId(token);
                Long userId = jwtService.extractUserId(token);

                request.setAttribute("email", email);
                request.setAttribute("rolId", rolId);
                request.setAttribute("userId", userId);

                filterChain.doFilter(request, response);

            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Invalid token or Expired\"}");
            }
        }catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Validation failed\"}");
        }




    }


    
}
