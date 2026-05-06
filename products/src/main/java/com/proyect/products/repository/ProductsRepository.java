package com.proyect.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.products.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    
}
