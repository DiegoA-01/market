package com.proyect.products.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.products.dto.ProductsRequest.ProductsRequestDTO;
import com.proyect.products.dto.ProductsResponseDTO.DeleteProductsDTO;
import com.proyect.products.dto.ProductsResponseDTO.ProductsResponseDTO;
import com.proyect.products.entity.Products;
import com.proyect.products.repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {
    @Autowired
    ProductsRepository productsRepository;
    
    /**
     * Metodo para crear producto
     * 
     * @param request
     * @return
     */
    public ProductsResponseDTO createProduct(ProductsRequestDTO request){

        if (productsRepository.existsByName(request.getName())) {
            throw new RuntimeException("El nombre del producto ya existe.");
        }
        Products products = new Products();
        
        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setPrice(request.getPrice());
        products.setStock(request.getStock());

        Products saveProducts = productsRepository.save(products);

        return toResponse(saveProducts);
    }

    /**
     * Metodo para Listar un producto
     * 
     * @return Lista
     */
    public List<ProductsResponseDTO> listProducts(){
        return productsRepository.findAll().stream().map(this::toResponse).toList();
    }

    /**
     * Metodo para buscar por id 
     * 
     * @param productId
     * @return
     */
    public ProductsResponseDTO showId(Long productId){
        Products products = productsRepository.findById(productId).orElseThrow(()-> new RuntimeException("Usuario no encontrado. "));
        return toResponse(products);
    }


    /**
     * Metodo para actualizar producto
     * 
     * @param productId
     * @param request
     * @return
     */
    public ProductsResponseDTO putProducts(Long productId, ProductsRequestDTO request){
        Products products = productsRepository.findById(productId).orElseThrow(()-> new RuntimeException("Usuario no encontrado. "));
        
        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setPrice(request.getPrice());
        products.setStock(request.getStock());

        Products productSave = productsRepository.save(products);

        return toResponse(productSave);
    }


    /**
     * Metodo de eliminar por id
     * 
     * @param productId
     */
    public DeleteProductsDTO deleteProductId(Long productId){
        productsRepository.findById(productId).orElseThrow(()-> new RuntimeException("Uusario no encontrado."));
        productsRepository.deleteById(productId);
        return DeleteProductsDTO.builder()
                .message("Producto eliminado correctamente.")
                .build();
    }


    /**
     * Metodo de respuesta para reducir codigo.
     * 
     * @param products
     * @return
     */
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
