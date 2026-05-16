package com.proyect.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.products.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    
    /**
     * Verifica si existe un producto por el nombre indicado. Se utiliza para evitar duplicados al crear o actualizar un producto.
     * 
     * @param name
     * @return
     */
    boolean existsByName(String name);
}
