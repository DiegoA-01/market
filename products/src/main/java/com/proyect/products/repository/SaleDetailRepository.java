package com.proyect.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyect.products.entity.SalesDetail;

public interface SaleDetailRepository extends JpaRepository<SalesDetail,Long> {
    
}
