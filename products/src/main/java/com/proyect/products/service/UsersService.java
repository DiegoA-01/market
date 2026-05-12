package com.proyect.products.service;

import org.springframework.stereotype.Service;

import com.proyect.products.dto.RequestDTO.UsersRequestDTO;
import com.proyect.products.dto.ResponseDTO.UsersResponseDTO;
import com.proyect.products.repository.UsersRepository;
import com.proyect.products.entity.Rol;
import com.proyect.products.entity.Users;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersResponseDTO createUser(UsersRequestDTO usersRequestDTO){
        // Lógica para crear un nuevo usuario
    
        if(usersRepository.existsByEmail(usersRequestDTO.getEmail())){
            throw new RuntimeException("El correo electrónico ya existe.");}

            Users users = new Users();

            users.setName(usersRequestDTO.getName());
            users.setEmail(usersRequestDTO.getEmail());
            users.setPassword(passwordEncoder.encode(usersRequestDTO.getPassword()));
            users.setPhone(usersRequestDTO.getPhone());
            users.setRol(Rol.valueOf(usersRequestDTO.getRol().toUpperCase()));

            Users saveUsers = usersRepository.save(users);

            return toResponse(saveUsers);


        
    }

    public List<UsersResponseDTO> listUsers(){
        return usersRepository.findAll().stream().map(this::toResponse).toList();
    }

    public UsersResponseDTO showId(Long userId){
        Users users = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("Usuario no encontrado. "));
        return toResponse(users);
    }

    public void deleteUser(Long userId){
        Users users = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("Usuario no encontrado. "));
        usersRepository.delete(users);
    }

    public UsersResponseDTO updateUser(Long userId, UsersRequestDTO usersRequestDTO){
        Users users = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("Usuario no encontrado. "));

        if(usersRepository.existsByEmail(usersRequestDTO.getEmail()) && !users.getEmail().equals(usersRequestDTO.getEmail())){
            throw new RuntimeException("El correo electrónico ya existe.");
        }

        users.setName(usersRequestDTO.getName());
        users.setEmail(usersRequestDTO.getEmail());
        users.setPassword(passwordEncoder.encode(usersRequestDTO.getPassword()));
        users.setPhone(usersRequestDTO.getPhone());
        users.setRol(Rol.valueOf(usersRequestDTO.getRol().toUpperCase()));

        Users saveUsers = usersRepository.save(users);

        return toResponse(saveUsers);
    }

    public UsersResponseDTO deleteUserResponse(Long userId){
        Users users = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("Usuario no encontrado. "));
        usersRepository.delete(users);
        return toResponse(users);
    }




    
    private UsersResponseDTO toResponse(Users users){
        return UsersResponseDTO.builder()
                .userId(users.getUserId())
                .name(users.getName())
                .email(users.getEmail())
                .phone(users.getPhone())
                .rol(users.getRol().name())
                .build();
    }

    
}
