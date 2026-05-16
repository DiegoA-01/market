package com.proyect.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.products.entity.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long>{
}
