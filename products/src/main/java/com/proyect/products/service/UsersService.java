package com.proyect.products.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyect.products.dto.ProductsRequest.UsersRequestDTO;
import com.proyect.products.dto.ProductsResponseDTO.DeleteUsersResponseDTO;
import com.proyect.products.dto.ProductsResponseDTO.UsersResponseDTO;
import com.proyect.products.entity.Users;
import com.proyect.products.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@RequiredArgsConstructor
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    /**
     * Metodo de crear
     * 
     * @param request
     * @return
     */
    public UsersResponseDTO createUser(UsersRequestDTO request){

        if (usersRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo ya existe, ingresa otro correo.");
        }
        if (usersRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("El numero ya existe.");
        }

        Users users = new Users();
        
        users.setName(request.getName());
        users.setEmail(request.getEmail());
        users.setPassword(request.getPassword());
        users.setPhone(request.getPhone());
        users.setRol(request.getRol());

        Users saveUsers = usersRepository.save(users);

        return toResponse(saveUsers);
    }

    /**
     * Metodo para listar Usuarios
     * 
     * @return
     */
    public List<UsersResponseDTO> listUsers(){
        return usersRepository.findAll().stream().map(this:: toResponse).toList();
    }


    /**
     * Metodo para Buscar por Id
     * 
     * @param userId
     * @return
     */
    public UsersResponseDTO showId(Long userId){
        Users users = usersRepository.findById(userId).orElseThrow(()->new RuntimeException("Usuario no encontrado."));
        return toResponse(users);
    }


    /**
     * Metodo para actualizar el usuario por Id
     * 
     * @param userId
     * @param request
     * @return
     */
    public UsersResponseDTO updatedId(Long userId, UsersRequestDTO request){
        Users users = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("Usuario no encontrado."));
        
        users.setName(request.getName());
        users.setEmail(request.getEmail());
        users.setPassword(request.getPassword());
        users.setPhone(request.getPhone());
        users.setRol(request.getRol());

        Users saveUser = usersRepository.save(users);

        return toResponse(saveUser);
    }

    /**
     * Metodo para eliminar por ID
     * 
     * @param userId
     */
    public DeleteUsersResponseDTO deletedId(Long userId){
        usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("Usuario no encontrado."));
        usersRepository.deleteById(userId);

        return DeleteUsersResponseDTO.builder()
                .message("Usuario eliminado correctamente.")
                .build();
    }


    /**
     * convierte una entidad Users en un UsersResponseDTO
     * 
     * @param users
     * @return
     */
    public UsersResponseDTO toResponse(Users users){
        return UsersResponseDTO.builder()
                .userId(users.getUserId())
                .name(users.getName())
                .email(users.getEmail())
                .password(users.getPassword())
                .phone(users.getPhone())
                .rol(users.getRol())
                .build();
    }
}
