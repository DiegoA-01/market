package com.proyect.products.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.products.dto.ProductsRequest.UsersRequestDTO;
import com.proyect.products.dto.ProductsResponseDTO.DeleteUsersResponseDTO;
import com.proyect.products.dto.ProductsResponseDTO.UsersResponseDTO;
import com.proyect.products.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;

    /**
     * Crea un nuevo usuario. Recibe un objeto UsersRequestDTO con los datos del usuario a crear y devuelve un objeto UsersResponseDTO con el resultado de la operación.
     * 
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UsersRequestDTO request){
        usersService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Usuario Creado exitosamente"));
    }

    /**
     * Lista todos los usuarios disponibles. Devuelve una lista de objetos UsersResponseDTO con los datos de cada usuario.
     * 
     * @return
     */
    @GetMapping
    public List<UsersResponseDTO> listUsers(){
        return usersService.listUsers();
    }

    /**
     * Obtiene los detalles de un usuario específico. Recibe el ID del usuario a consultar y devuelve un objeto UsersResponseDTO con los datos del usuario correspondiente.
     * 
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public UsersResponseDTO showId(@Valid @PathVariable Long  userId){
        return usersService.showId(userId);
    }

    /**
     * Actualiza los datos de un usuario específico. Recibe el ID del usuario a actualizar y un objeto UsersRequestDTO con los nuevos datos.
     * 
     * @param userId
     * @param request
     * @return
     */
    @PutMapping("/{userId}")
    public UsersResponseDTO updatedId(@PathVariable Long userId, @Valid @RequestBody UsersRequestDTO request){
        return usersService.updatedId(userId, request);
    }

    /**
     * Elimina un usuario específico. Recibe el ID del usuario a eliminar.
     * 
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<DeleteUsersResponseDTO> deleteId(@PathVariable Long userId){
        return ResponseEntity.ok(usersService.deletedId(userId));
    }
}
