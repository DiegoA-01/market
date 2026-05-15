package com.proyect.products.controller;


import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;


import jakarta.servlet.http.HttpServletRequest;


import com.proyect.products.dto.RequestDTO.SalesRequestDTO;
import com.proyect.products.dto.ResponseDTO.MessageResponseDTO;
import com.proyect.products.dto.ResponseDTO.SalesResponseDTO;
import com.proyect.products.service.SalesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createSale(@RequestBody SalesRequestDTO request, HttpServletRequest httpRequest) {
        salesService.createSale(request, httpRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDTO("venta exitosa"));
    }

    @GetMapping
    public ResponseEntity<List<SalesResponseDTO>> getAllSales(HttpServletRequest httpRequest) {
        List<SalesResponseDTO> response = salesService.getAllSales(httpRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesResponseDTO> getSaleById(@PathVariable Long id, HttpServletRequest httpRequest) {
        SalesResponseDTO response = salesService.getSaleById(id, httpRequest);
        return ResponseEntity.ok(response);
    }

    
}

