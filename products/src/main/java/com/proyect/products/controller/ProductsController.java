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
import com.proyect.products.dto.ProductsResponseDTO.DeleteProductsDTO;
import com.proyect.products.dto.ProductsResponseDTO.ProductsResponseDTO;
import com.proyect.products.service.ProductsService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    ProductsService productsService;

    /**
     * Crea un nuevo producto. Recibe un objeto ProductsRequestDTO con los datos del producto a crear y devuelve un objeto ProductsResponseDTO con el resultado de la operación.
     * 
     * @param request
     * @return
     */
    @PostMapping
    public ProductsResponseDTO createProduct(@Valid @RequestBody ProductsRequestDTO request){
        return productsService.createProduct(request);
    }

    /**
     * Lista todos los productos disponibles. Devuelve una lista de objetos ProductsResponseDTO con los datos de cada producto.
     * 
     * @return
     */
    @GetMapping
    public List<ProductsResponseDTO> listProducts(){
        return productsService.listProducts();
    }

    /**
     * 
     * 
     * @param productId
     * @return
     */
    @GetMapping("/{productId}")
    public ProductsResponseDTO showId(@Valid @PathVariable Long productId){
        return productsService.showId(productId);
    }

    /**
     * Actualiza un producto existente. Recibe un objeto ProductsRequestDTO con los datos actualizados del producto y devuelve un objeto ProductsResponseDTO con el resultado de la operación.
     * 
     * @param productId
     * @param request
     * @return
     */
    @PutMapping("/{productId}")
    public ProductsResponseDTO putProducts(@PathVariable Long productId, @Valid @RequestBody ProductsRequestDTO request){
        return productsService.putProducts(productId, request);
    }

    /**
     * Elimina un producto existente. Recibe el ID del producto a eliminar y devuelve un objeto DeleteProductsDTO con el resultado de la operación.
     * 
     * @param productId
     * @return
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<DeleteProductsDTO> deleteProductId(@PathVariable Long productId){
        return ResponseEntity.ok(productsService.deleteProductId(productId));
    }
}
