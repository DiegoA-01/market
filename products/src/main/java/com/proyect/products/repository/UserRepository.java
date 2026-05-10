package com.proyect.products.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.products.entity.UserEntity;



public interface UserRepository extends JpaRepository<UserEntity, Long> {
  
  Boolean existByEmail(String email);

  Optional<UserEntity> findByEmail(String email);
}
