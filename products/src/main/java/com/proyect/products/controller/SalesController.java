package com.proyect.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.products.dto.ProductsRequest.SalesRequestDTO;
import com.proyect.products.dto.ProductsResponseDTO.SalesResponseDTO;
import com.proyect.products.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    SalesService salesService;

    @PostMapping
    public ResponseEntity<SalesResponseDTO> createSale(@RequestBody SalesRequestDTO request){
        SalesResponseDTO response = salesService.createSale(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SalesResponseDTO>> getAllSales(){
        return ResponseEntity.ok(salesService.getAllSales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesResponseDTO> getSaleById(@PathVariable Long id){
        
        return ResponseEntity.ok(salesService.getSaleById(id));
    }
}
