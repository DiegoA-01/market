package com.proyect.products.service;

import org.springframework.stereotype.Service;

import com.proyect.products.repository.usersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class usersService {
  
  com.proyect.products.repository.usersRepository usersRepository;


  public UsersResponseDTO createUser(UsersRequestDTo) {

    if(usersRepository.existsByUserName(request.getUserName())) {
      throw new RuntimeException("This user exist with this name.");
    }
    
    UserNames userNames = new UserNames();

    userNames.setName(request.getName());
    userNames.setUserName(request.getUsername());
    userNames.setPassword(request.getPassword());
    userNames.setRol(request.getRol());

    UserNames saveUserNames = usersRepository.save(userNames);

    return toResponse(saveUserNames);
  }
}
