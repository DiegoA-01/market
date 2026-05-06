package com.proyect.products.service;

import org.springframework.stereotype.Service;

import com.proyect.products.dto.ProductsResponseDTO.ProductsResponseDTO;
import com.proyect.products.entity.Products;
import com.proyect.products.repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {
    
    ProductsRepository productsRepository;










    public ProductsResponseDTO toResponse(Products products){
        return ProductsResponseDTO.builder()
                .productId(products.getProductId())
                .name(products.getName())
                .description(products.getDescription())
                .price(products.getPrice())
                .stock(products.getStock())
                .build();
    }
}
