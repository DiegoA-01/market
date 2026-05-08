package com.proyect.products.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyect.products.dto.ProductsRequest.ProductsRequestDTO;
import com.proyect.products.dto.ProductsResponseDTO.ProductsResponseDTO;
import com.proyect.products.entity.Products;
import com.proyect.products.repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {
    
    ProductsRepository productsRepository;

    public ProductsResponseDTO createProduct(ProductsRequestDTO request){
        Products products = new Products();
        
        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setPrice(request.getPrice());
        products.setStock(request.getStock());

        Products saveProducts = productsRepository.save(products);

        return toResponse(saveProducts);
    }

    public List<ProductsResponseDTO> listProducts(){
        return productsRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ProductsResponseDTO showId(Long productId){
        Products products = productsRepository.findById(productId).orElseThrow(()-> new RuntimeException("Usuario no encontrado. "));
        return toResponse(products);
    }

    public ProductsResponseDTO putProducts(Long productId, ProductsRequestDTO request){
        Products products = productsRepository.findById(productId).orElseThrow(()-> new RuntimeException("Usuario no encontrado. "));
        
        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setPrice(request.getPrice());
        products.setStock(request.getStock());

        Products productSave = productsRepository.save(products);

        return toResponse(productSave);
    }

    public void deleteProductId(Long productId){
        productsRepository.findById(productId);
    }


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
