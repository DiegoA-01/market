package com.proyect.products.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.products.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
    
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    Optional <Users> finByEMail(String email);
}
