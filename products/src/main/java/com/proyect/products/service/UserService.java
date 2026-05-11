package com.proyect.products.service;

import java.util.List;

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

  // is important to fix crate user, beacause is not byEmail is byId 

  /**
   * Method to create user
   * 
   * @param request
   * @return
   */
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

  /**
   * method to list a user
   * 
   * @return
   */
  public List<UserResponseDTO> listUsers() {
    return userRepository.findAll().stream().map(this::toResponse).toList();
  }
  
  /**
   * Method to find by Id.
   * 
   * @param userId
   * @return
   */
  public UserResponseDTO showId(Long userId) {
    UserEntity users = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found."));
    return toResponse(users);
  }

  /**
   * Methos to update user
   * 
   * @param userId
   * @param request
   * @return
   */
  public UserResponseDTO putUser(Long userId, UserRequestDTO request) {
    UserEntity users = UserRepository.findById(userId).orElseThrow(()-> new RuntimeException("User update"));

    users.setName(request.getName());
    users.setEmail(request.getEmail());
    users.setPassword(request.getPassword());
    users.setPhone(request.getPhone());
    users.setRole(request.getRole());

    UserEntity userSave = userRepository.save(users);

    return toResponse(userSave);
  }

  /**
   * Method to delete by id.
   * 
   * @param userId
   */
  public void deleteUserById(Long userId) {
    userRepository.findById(userId);
  }

  /**
   * Method for reducing code.
   * 
   * @param user
   * @return
   */
  public UserResponseDTO toResponse(UserEntity user) {
    return UserResponseDTO.builder()
            .userId(user.getUserId())
            .name(user.getName())
            .email(user.getEmail())
            .password(user.getPassword())
            .phone(user.getPhone())
            .role(user.getRole())
            .build();
  }
}
