package com.proyect.products.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.proyect.products.dto.ProductsRequest.UserRequestDTO;

import com.proyect.products.dto.ProductsResponseDTO.UserResponseDTO;
import com.proyect.products.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/Users")
public class UserController {
  
  UserService userService;

  @PostMapping
  public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO request) {
    return userService.createUser(request);
  }

  @GetMapping
  public List<UserResponseDTO> listUser() {
    return userService.listUsers();
  }

  @GetMapping("/{id}")
  public UserResponseDTO showId(@Valid @PathVariable Long userId) {
    return userService.showId(userId);
  }

@PutMapping("/{id}")
    public UserResponseDTO putUser(@Valid @PathVariable Long userId, UserRequestDTO request){
        return userService.putUser(userId, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@Valid @PathVariable Long userId){
        userService.deleteUserById(userId);
    }
  
  
  
  
}
