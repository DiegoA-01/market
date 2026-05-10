package com.proyect.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.products.entity.usersEntity;
import java.util.Optional;


public interface usersRepository extends JpaRepository<usersEntity, Long> {
  
  boolean existsByUsername(String username);

  Optional<usersEntity> findByUserName(String username);

}
