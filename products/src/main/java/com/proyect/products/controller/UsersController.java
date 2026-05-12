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

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UsersRequestDTO request){
        usersService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Usuario Creado exitosamente"));
    }

    @GetMapping
    public List<UsersResponseDTO> listUsers(){
        return usersService.listUsers();
    }

    @GetMapping("/{userId}")
    public UsersResponseDTO showId(@Valid @PathVariable Long  userId){
        return usersService.showId(userId);
    }

    @PutMapping("/{userId}")
    public UsersResponseDTO updatedId(@PathVariable Long userId, @Valid @RequestBody UsersRequestDTO request){
        return usersService.updatedId(userId, request);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<DeleteUsersResponseDTO> deleteId(@PathVariable Long userId){
        return ResponseEntity.ok(usersService.deletedId(userId));
    }
}
