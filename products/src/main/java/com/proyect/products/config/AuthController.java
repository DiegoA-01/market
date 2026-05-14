package com.proyect.products.config;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;

import com.proyect.products.dto.ResponseDTO.MessageResponseDTO;
import com.proyect.products.dto.RequestDTO.LoginRequestDTO;
import com.proyect.products.dto.RequestDTO.RegisterRequestDTO;
import com.proyect.products.dto.ResponseDTO.LoginResponseDTO;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import com.proyect.products.dto.ResponseDTO.RefreshTokenResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
public ResponseEntity<MessageResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
    MessageResponseDTO response = authService.register(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        try {
            LoginResponseDTO response = authService.login(request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new LoginResponseDTO(null, "error en login, Credenciales invalidas"));
        }

    }
    
    @GetMapping("/refresh")
    public ResponseEntity<RefreshTokenResponseDTO> refreshToken(HttpServletRequest request){
        String autHeader = request.getHeader("Authorization");
        if (autHeader == null || !autHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(new RefreshTokenResponseDTO(null ,"Token invalido o no presente"));
        }

        String token = autHeader.replace("Bearer ", "");

        RefreshTokenResponseDTO response = new RefreshTokenResponseDTO();
        
        try {
            response = authService.refreshToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e){
            response.setMessage("Token Expired");
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new RefreshTokenResponseDTO(null, "Token Expired"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new RefreshTokenResponseDTO(null, "Error al refrescar el token"));
    }
    }
    



    
    
}
