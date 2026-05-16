package com.proyect.products.controller;

import org.springframework.web.bind.annotation.RestController;

import com.proyect.products.dto.SalesRequestDTO.SalesRequestDTO;
import com.proyect.products.dto.SalesResponseDTO.SalesResponseDTO;
import com.proyect.products.service.SalesService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesController {
    
    private final SalesService salesService;

    /**
     * Metodo para listar todas las ventas
     * GET /api/sales
     */
    @GetMapping
    public ResponseEntity<List<SalesResponseDTO>> listSales() {
        return ResponseEntity.ok(salesService.listSales());
    }

    /**
     * Metodo para buscar venta por id
     * @param Id
     * @return
     */
    @GetMapping("/{Id}")
    public ResponseEntity<SalesResponseDTO> showId(@PathVariable Long Id) {
        return ResponseEntity.ok(salesService.showId(Id));
    }

    /**
     * Metodo para crear una venta
     * @param requestDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<SalesResponseDTO> createSale(@Valid @RequestBody SalesRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salesService.createSale(requestDTO));
    }

    /**
     * Metodo para actualizar una venta
     * @param id
     * @param requestDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<SalesResponseDTO> updateSale(@PathVariable Long id, @Valid @RequestBody SalesRequestDTO requestDTO) {
        return ResponseEntity.ok(salesService.putSale(id, requestDTO));
    }

    /**
     * Metodo para eliminar una venta
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleId(@PathVariable Long id) {
        salesService.deleteSaleId(id);
        return ResponseEntity.noContent().build();
    }
    
}
