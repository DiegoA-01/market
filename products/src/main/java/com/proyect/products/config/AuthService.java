package com.proyect.products.config;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyect.products.dto.ProductsRequest.LoginRequestDTO;
import com.proyect.products.dto.ProductsRequest.RegisterRequestDTO;
import com.proyect.products.dto.ProductsResponseDTO.LoginResponseDTO;
import com.proyect.products.dto.ProductsResponseDTO.MenssageResponseDTO;
import com.proyect.products.dto.ProductsResponseDTO.RefreshResponseDTO;
import com.proyect.products.entity.Users;
import com.proyect.products.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UsersRepository usersRepository;

    public MenssageResponseDTO register(RegisterRequestDTO register){
        MenssageResponseDTO response = new MenssageResponseDTO();

        response.setMenssage("Registro exitoso");
        if (usersRepository.findByEmail(register.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya existe intenta con otro");
        }
        Users users = new Users();
        users.setName(register.getName());
        users.setEmail(register.getEmail());
        users.setPassword(passwordEncoder.encode(register.getPassword()));
        users.setPhone(register.getPhone());
        users.setRol(register.getRol());

        usersRepository.save(users);
        return response;
    }

    public LoginResponseDTO login(LoginRequestDTO request){
        LoginResponseDTO response = new LoginResponseDTO();

        Optional<Users> userOpt = usersRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty() && request.getEmail() != null) {
            response.setMessage("El usuario ya se encuentra registrado");
            return response;
        }
        Users userFound = userOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), userFound.getPassword())) {
            response.setMessage("Contraseña incorrecta");
            return response;
        }

        String jwt = jwtService.generatedToken(userFound);
        response.setJwt(jwt);
        response.setMessage("Ingreso con exito");

        return response;
    }

    public RefreshResponseDTO tokenRefresh(String token) throws Exception{
        String jwt = jwtService.refreshToken(token);
        RefreshResponseDTO reponse = new RefreshResponseDTO();
        
        reponse.setJwt(jwt);
        reponse.setMessage("El token se refresco exitosamente");

        return reponse;
    }


}
