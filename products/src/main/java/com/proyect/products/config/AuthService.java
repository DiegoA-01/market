package com.proyect.products.config;


import org.springframework.stereotype.Service;

import com.proyect.products.entity.Rol;
import com.proyect.products.entity.Users;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proyect.products.dto.ResponseDTO.RefreshTokenResponseDTO;
import com.proyect.products.dto.RequestDTO.LoginRequestDTO;
import com.proyect.products.dto.RequestDTO.RegisterRequestDTO;
import com.proyect.products.dto.ResponseDTO.LoginResponseDTO;
import com.proyect.products.dto.ResponseDTO.MessageResponseDTO;
import com.proyect.products.repository.UsersRepository; 
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    /**
     * incriptacion de la contraseña.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * genera y valida un token.
     */
    private final JwtService jwtService;

    /**
     * Maneja las operaciones sobre los usuarios
     */
    private final UsersRepository userRepository;


    /**
     * Genera un nuevo usuario pero validando si el correo no existe en otro osuario, luego encripta la contraseña y crea el usuario
     * 
     * @param request
     * @return
     */
    public MessageResponseDTO register(RegisterRequestDTO request){
        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage("Registration successful");

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
    }

    Users user = new Users();

    user.setEmail(request.getEmail());
    user.setName(request.getName());
    user.setPhone(request.getPhone());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRol(Rol.valueOf(request.getRol().toString().toUpperCase()));

    userRepository.save(user);
    return response;
}

/**
 * verifica que el email y la contraseña sean validas para generar el token
 * 
 * @param request
 * @return
 */
public LoginResponseDTO login(LoginRequestDTO request){
    LoginResponseDTO response = new LoginResponseDTO();
    Optional<Users> userOpt = userRepository.findByEmail(request.getEmail());

    if (userOpt.isEmpty() || request.getEmail() == null){
        response.setMessage("email invalido");
        return response;
    }

    Users userFound = userOpt.get();

    if(!passwordEncoder.matches(request.getPassword(), userFound.getPassword())){
        response.setMessage("contraseña incorrecta");
        return response;
    }

    String jwt = jwtService.generatedToken(userFound);
    response.setJwt(jwt);
    response.setMessage("Login successful");
    return new LoginResponseDTO(jwt, "Login successful");

}

/**
 * genera un nuevo token con un token valido ya existente
 * 
 * @param token
 * @return
 * @throws Exception
 */
public RefreshTokenResponseDTO refreshToken(String token)throws Exception{
    
    String jwt = jwtService.refreshToken(token);
    RefreshTokenResponseDTO response = new RefreshTokenResponseDTO();
    response.setJwt(jwt);
    response.setMessage("Token refreshed successfully");
    return response;
}

}
