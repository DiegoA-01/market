package com.proyect.products.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.proyect.products.dto.RequestDTO.UsersRequestDTO;
import com.proyect.products.service.UsersService;
import com.proyect.products.dto.ResponseDTO.UsersResponseDTO;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersService userService;

    
    public UserController(UsersService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UsersResponseDTO createUser(@Valid @RequestBody UsersRequestDTO request){
        return userService.createUser(request);
    }

    @GetMapping
    public List<UsersResponseDTO> listUsers(){
        return userService.listUsers();
    }

    @GetMapping("/{userId}")
    public UsersResponseDTO showId(@PathVariable Long userId){
        return userService.showId(userId);
    }

    @PutMapping("/{userId}")
    public UsersResponseDTO updateUser(@PathVariable Long userId,@Valid @RequestBody UsersRequestDTO request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}

