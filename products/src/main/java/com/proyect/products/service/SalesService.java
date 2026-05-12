package com.proyect.products.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.products.dto.ProductsRequest.SalesRequestDTO;
import com.proyect.products.dto.ProductsResponseDTO.SalesResponseDTO;
import com.proyect.products.entity.Products;
import com.proyect.products.entity.Sales;
import com.proyect.products.entity.Users;
import com.proyect.products.repository.ProductsRepository;
import com.proyect.products.repository.SaleRepository;
import com.proyect.products.repository.UsersRepository;

@Service
public class SalesService {
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ProductsRepository productsRepository;

    public SalesResponseDTO createSale(SalesRequestDTO request){

        Users users = usersRepository.findById(request.getUserId()).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        
        Products products = productsRepository.findById(request.getProductId()).orElseThrow(()-> new RuntimeException("Producto no encontrado."));

        if (request.getCantidad() > products.getStock()) {
            throw new RuntimeException("Stock insuficiente. Disponible: " + products.getStock());
        }

        /**
         * Descontar Stock
         */
        products.setStock(products.getStock() - request.getCantidad());
        productsRepository.save(products);

        /**
         * Calcular Subtotal
         */
        BigDecimal subtotal = products.getPrice()
            .multiply(new BigDecimal(request.getCantidad()));

        BigDecimal total = subtotal.multiply(BigDecimal.valueOf(1.21));

        Sales sales = new Sales();

        sales.setUsers(users);
        sales.setProducts(products);
        sales.setSubTotal(subtotal);
        sales.setTotal(total);
        sales.setCantidad(request.getCantidad());
        sales.setFecha(LocalDateTime.now());

        Sales saveSale = saleRepository.save(sales);

        return toResponse(saveSale);
    }

    public List<SalesResponseDTO> getAllSales(){

        List<Sales> sales = saleRepository.findAll();

        return sales.stream()
                .map(this::toResponse)
                .toList();
    }


    public SalesResponseDTO getSaleById(Long id){

        Sales sales = saleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venta no encontrada")
                );

        return toResponse(sales);
    }


    public SalesResponseDTO toResponse(Sales sales){
        return SalesResponseDTO.builder()
                .idSale(sales.getIdSale())
                .fecha(sales.getFecha())
                .subtotal(sales.getSubTotal())
                .total(sales.getTotal())
                .userName(sales.getUsers().getName())
                .productName(sales.getProducts().getName())
                .build();
    }
}
