package com.proyect.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.products.dto.ProductsRequest.SalesDetailRequest;
import com.proyect.products.entity.SalesDetail;
import com.proyect.products.service.SalesDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sales-detail")
@RequiredArgsConstructor
public class SalesDetailController {
    @Autowired
    SalesDetailService salesDetailService;

    /**
     * Crear detalle de venta
     * 
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<SalesDetail> createSalesDetail(@RequestBody SalesDetailRequest request){

        SalesDetail detail = salesDetailService.createSalesDetail(request.getIdSale(), request.getUserId(), request.getProductId(), request.getCantidad());

        return ResponseEntity.ok(detail);
    }

    /**
     * Lista todos los detalles de venta disponibles.
     * 
     * @return
     */
    @GetMapping
    public ResponseEntity<List<SalesDetail>> getAll(){
        return ResponseEntity.ok(salesDetailService.getAll());
    }

    /**
     * Obtiene todos los detalles de venta para una venta específica.
     * 
     * @param saleId
     * @return
     */
    @GetMapping("/sale/{saleId}")
    public ResponseEntity<List<SalesDetail>> getBySale(@PathVariable Long saleId){
        return ResponseEntity.ok(salesDetailService.getBySale(saleId));
    }
}
