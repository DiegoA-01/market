package com.proyect.products.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.products.dto.ProductsRequest.ProductsRequestDTO;
import com.proyect.products.dto.ProductsResponseDTO.ProductsResponseDTO;
import com.proyect.products.service.ProductsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Products")
public class ProductsController {
    ProductsService productsService;

    @PostMapping
    public ProductsResponseDTO createProduct(@Valid @RequestBody ProductsRequestDTO request){
        return productsService.createProduct(request);
    }

    @GetMapping
    public List<ProductsResponseDTO> listProducts(){
        return productsService.listProducts();
    }

    @GetMapping("/{id}")
    public ProductsResponseDTO showId(@Valid @PathVariable Long productId){
        return productsService.showId(productId);
    }

    @PutMapping("/{id}")
    public ProductsResponseDTO putProducts(@Valid @PathVariable Long productId, ProductsRequestDTO request){
        return productsService.putProducts(productId, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProductId(@Valid @PathVariable Long productId){
        productsService.deleteProductId(productId);
    }
}
