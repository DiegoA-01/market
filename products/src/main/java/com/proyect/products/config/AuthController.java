package com.proyect.products.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.products.dto.ProductsRequest.LoginRequestDTO;
import com.proyect.products.dto.ProductsRequest.RegisterRequestDTO;
import com.proyect.products.dto.ProductsResponseDTO.LoginResponseDTO;
import com.proyect.products.dto.ProductsResponseDTO.MenssageResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<MenssageResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        MenssageResponseDTO response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
