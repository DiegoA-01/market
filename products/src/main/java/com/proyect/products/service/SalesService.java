package com.proyect.products.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.products.dto.RequestDTO.ProductQuantityDTO;
import com.proyect.products.dto.RequestDTO.SalesRequestDTO;
import com.proyect.products.dto.ResponseDTO.DeleteDTO;
import com.proyect.products.dto.ResponseDTO.SalesDetailResponseDTO;
import com.proyect.products.dto.ResponseDTO.SalesResponseDTO;
import com.proyect.products.entity.Products;
import com.proyect.products.entity.Sales;
import com.proyect.products.entity.SalesDetail;
import com.proyect.products.entity.Users;
import com.proyect.products.repository.ProductsRepository;
import com.proyect.products.repository.SaleRepository;
import com.proyect.products.repository.UsersRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class SalesService {
    private final PermissionService permissionService;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ProductsRepository productsRepository;



    public SalesResponseDTO createSale(SalesRequestDTO request, HttpServletRequest httpServletRequest){


        Long userId = (Long) httpServletRequest.getAttribute("userId");
        
        if(userId == null){
            throw new RuntimeException("userId no esta en el request");
        }
        String rol = (String) httpServletRequest.getAttribute("rol");
        permissionService.checkAdminOrCashier(rol);

        Users users = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        int cantidadTotal = 0;
        BigDecimal subTotal = BigDecimal.ZERO;

        List<SalesDetail> detalles = new ArrayList<>();

        Sales sales = new Sales();


        for(ProductQuantityDTO pq : request.getProducts()){

            Products product = productsRepository.findById(pq.getProductId()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if(pq.getCantidad() > product.getStock()){
                throw new RuntimeException("Stock insuficiente, Disponible: " + product.getStock());
            }

            product.setStock(product.getStock() - pq.getCantidad());
        productsRepository.save(product);

        BigDecimal sub = product.getPrice().multiply(BigDecimal.valueOf(pq.getCantidad()));

        subTotal = subTotal.add(sub);
        cantidadTotal += pq.getCantidad();

        SalesDetail detail = new SalesDetail();
        detail.setSale(sales);
        detail.setProduct(product);
        detail.setCantidad(pq.getCantidad());
        detail.setPrecioUnitario(product.getPrice());
        detail.setSubtotal(sub);

        detalles.add(detail);

        }

        
        BigDecimal total = subTotal.multiply(BigDecimal.valueOf(1.21));

        

        sales.setUsers(users);
        sales.setSubTotal(subTotal);
        sales.setTotal(total);
        sales.setCantidad(cantidadTotal);
        sales.setFecha(LocalDateTime.now());
        sales.setDetalles(detalles);

        detalles.forEach(d -> d.setSale(sales));
        
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

    


    public SalesResponseDTO toResponse(Sales sales, String rol){

        List<SalesDetailResponseDTO> detalles = sales.getDetalles().stream()
                                                    .map(detail -> SalesDetailResponseDTO.builder()
                                                    .productId(detail.getProduct().getProductId())
                                                    .nombre(detail.getProduct().getName())
                                                    .cantidad(detail.getCantidad())
                                                    .precioUnitario(detail.getPrecioUnitario())
                                                    .subtotal(detail.getSubtotal())
                                                    .build())
                                                .toList();                                               

        return SalesResponseDTO.builder()
                .idSale(sales.getIdSale())
                .fecha(sales.getFecha())
                .subtotal(sales.getSubTotal())
                .total(sales.getTotal())
                .userName(sales.getUsers().getName())
                .cantidad(sales.getCantidad())
                .detalles(detalles)
                .build();
    }
}
