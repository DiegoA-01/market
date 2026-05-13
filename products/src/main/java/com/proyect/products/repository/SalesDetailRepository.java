package com.proyect.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.products.entity.SalesDetail;

public interface SalesDetailRepository extends JpaRepository<SalesDetail, Long>{
    
    /**
     * Buscar detalles por usuario
     * 
     * @param userId
     * @return
     */
    List<SalesDetail> findByUserUserId(Long userId);

    /**
     * Buscar detalles por producto
     * 
     * @param productId
     * @return
     */
    List<SalesDetail> findByProductProductId(Long productId);

    /**
     * Buscar por venta
     * 
     * @param saleId
     * @return
     */
    List<SalesDetail> findBySaleIdSale(Long idSale);
}
