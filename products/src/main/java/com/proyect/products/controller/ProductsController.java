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

import com.proyect.products.dto.RequestDTO.ProductsRequestDTO;
import com.proyect.products.dto.ResponseDTO.DeleteProductsDTO;
import com.proyect.products.dto.ResponseDTO.MessageResponseDTO;
import com.proyect.products.dto.ResponseDTO.ProductsResponseDTO;
import com.proyect.products.service.PermissionService;
import com.proyect.products.service.ProductsService;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    @Autowired
    ProductsService productsService;
    private final PermissionService permissionService;


    @PostMapping("/secure")
    public ResponseEntity<?> createProduct(HttpServletRequest request, @RequestBody ProductsRequestDTO productsRequestDTO){
        String rol = (String) request.getAttribute("rol");
        permissionService.checkAdmin(rol);

        productsService.createProduct(productsRequestDTO);
        return ResponseEntity.ok(new MessageResponseDTO("Producto creado"));
    }

    @GetMapping
    public List<ProductsResponseDTO> listProducts(HttpServletRequest httpRequest){
        String rol =(String) httpRequest.getAttribute ("rol");
        permissionService.checkAdminOrCashier(rol);
        return productsService.listProducts();
    }

    @GetMapping("/{productId}")
    public ProductsResponseDTO showId(@Valid @PathVariable Long productId,HttpServletRequest httpRequest){
        String rol = (String) httpRequest.getAttribute("rol");
        permissionService.checkAdminOrCashier(rol);
        return productsService.showId(productId);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductsRequestDTO request,HttpServletRequest httpRequest){

    String rol = (String) httpRequest.getAttribute("rol");
    permissionService.checkAdmin(rol);

    ProductsResponseDTO updated = productsService.putProducts(id,request);
    return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId, @Valid @RequestBody ProductsRequestDTO productsRequestDTO,HttpServletRequest httpsRequest){
        String rol = (String) httpsRequest.getAttribute("rol");
        permissionService.checkAdmin(rol);

        DeleteProductsDTO deleted = productsService.deleteProductId(productId);
        return ResponseEntity.ok(deleted);

    
    }
}
