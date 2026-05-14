package com.proyect.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.products.entity.Sales;

public interface SaleRepository extends JpaRepository<Sales,Long>{
}
