package com.proyect.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyect.products.entity.Users;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);
    Optional<Users> findByEmail(String email);
}



