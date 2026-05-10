package com.proyect.products.service;

import org.springframework.stereotype.Service;

import com.proyect.products.dto.ProductsRequest.UserRequestDTO;
import com.proyect.products.dto.ProductsResponseDTO.UserResponseDTO;
import com.proyect.products.entity.UserEntity;
import com.proyect.products.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  UserRepository userRepository;

  public UserResponseDTO createUser(UserRequestDTO request) {

    if (UserRepository.existByEmail(request.getEmail())) {
      throw new RuntimeException("This user already exist.");
    }

    UserEntity Users = new UserEntity();

    Users.setName(request.getName());
    Users.setEmail(request.getEmail());
    Users.setPassword(request.getPassword());
    Users.setPhone(request.getPhone());
    Users.setRole(request.getRole());

    UserEntity saveUsers = userRepository.save(Users);

    return toResponse(saveUsers);
  }
  
}
