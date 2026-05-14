package com.proyect.products.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.products.dto.RequestDTO.SalesRequestDTO;
import com.proyect.products.dto.ResponseDTO.DeleteDTO;
import com.proyect.products.dto.ResponseDTO.SalesResponseDTO;
import com.proyect.products.entity.Products;
import com.proyect.products.entity.Sales;
import com.proyect.products.entity.Users;
import com.proyect.products.repository.ProductsRepository;
import com.proyect.products.repository.SaleRepository;
import com.proyect.products.repository.UsersRepository;

import jakarta.servlet.http.HttpServletRequest;

import com.proyect.products.dto.ResponseDTO.UsersResponseDTO;


@Service
public class SalesService {
    private final PermissionService permissionService;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ProductsRepository productsRepository;

    SalesService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public SalesResponseDTO createSale(SalesRequestDTO request, HttpServletRequest httpServletRequest){


        Long userId = (Long) httpServletRequest.getAttribute("userId");
        if(userId == null){
            throw new RuntimeException("userId no esta en el request");
        }
        String rol = (String) httpServletRequest.getAttribute("rol");
        permissionService.checkAdminOrCashier(rol);

        Users users = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        
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

        return toResponse(saveSale, rol);
    }

    public List<SalesResponseDTO> getAllSales(HttpServletRequest httpServletRequest){
        String rol = (String) httpServletRequest.getAttribute("rol");
        permissionService.checkAdminOrCashier(rol);

        return saleRepository.findAll()
                .stream()
                .map(s -> toResponse(s, rol))
                .toList();
    }


    public SalesResponseDTO getSaleById(Long id,HttpServletRequest httpServletRequest){

        String rol = (String) httpServletRequest.getAttribute("rol");
        permissionService.checkAdminOrCashier(rol);

        Sales sales = saleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venta no encontrada")
                
                );

        return toResponse(sales,rol);
    }

    public DeleteDTO deletedId(Long idSale, HttpServletRequest httpServletRequest){

        String rol = (String) httpServletRequest.getAttribute("rol");
        permissionService.checkAdmin(rol);

        saleRepository.findById(idSale).orElseThrow(()-> new RuntimeException("Usuario no encontrado."));
        saleRepository.deleteById(idSale);

        return DeleteDTO.builder()
                .message("Usuario eliminado correctamente.")
                .build();
    }


    public SalesResponseDTO toResponse(Sales sales, String rol){
        return SalesResponseDTO.builder()
                .idSale(sales.getIdSale())
                .fecha(sales.getFecha())
                .subtotal(sales.getSubTotal())
                .total(sales.getTotal())
                .userName(sales.getUsers().getName())
                .userRole(rol)
                .productName(sales.getProducts().getName())
                .cantidad(sales.getCantidad())
                .build();
    }
}
