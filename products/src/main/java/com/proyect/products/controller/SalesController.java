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

    /**
     * Crea una nueva venta. Recibe un objeto SalesRequestDTO con los datos de la venta a crear y devuelve un objeto SalesResponseDTO con el resultado de la operación.
     * 
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<SalesResponseDTO> createSale(@RequestBody SalesRequestDTO request){
        SalesResponseDTO response = salesService.createSale(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Lista todas las ventas disponibles. Devuelve una lista de objetos SalesResponseDTO con los datos de cada venta.
     * 
     * @return
     */
    @GetMapping
    public ResponseEntity<List<SalesResponseDTO>> getAllSales(){
        return ResponseEntity.ok(salesService.getAllSales());
    }

    /**
     * Obtiene los detalles de una venta específica. Recibe el ID de la venta a consultar y devuelve un objeto SalesResponseDTO con los datos de la venta correspondiente.
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<SalesResponseDTO> getSaleById(@PathVariable Long id){
        
        return ResponseEntity.ok(salesService.getSaleById(id));
    }
}
