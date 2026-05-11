package com.proyect.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.products.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
