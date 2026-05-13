package com.proyect.products.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyect.products.entity.Products;
import com.proyect.products.entity.Sales;
import com.proyect.products.entity.SalesDetail;
import com.proyect.products.entity.Users;
import com.proyect.products.repository.ProductsRepository;
import com.proyect.products.repository.SaleRepository;
import com.proyect.products.repository.SalesDetailRepository;
import com.proyect.products.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesDetailService {
    @Autowired
    SalesDetailRepository salesDetailRepository;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    SaleRepository saleRepository;

    public SalesDetail createSalesDetail(Long idSale, Long userId, Long productId, Integer cantidad){
        //Valiida la cantidad
        if (cantidad <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }

        //Busca el producto
        Products products = productsRepository.findById(productId).orElseThrow(()-> new RuntimeException("Producto no encontrado."));

        if (products.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficinete.");
        }

        //Busca usuario
        Users user = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        //Busca venta 
        Sales sale = saleRepository.findById(idSale).orElseThrow(()-> new RuntimeException("Venta no encontrada"));

        SalesDetail detail = new SalesDetail();
        detail.setCantidad(cantidad);
        detail.setPrecioUnitario(products.getPrice());
        detail.setProduct(products);
        detail.setUser(user);
        detail.setSale(sale);

        //Descuenta el stock
        products.setStock(products.getStock() - cantidad);
        productsRepository.save(products);

        return salesDetailRepository.save(detail);
    }

    /**
     * Listar todo
     * 
     * @return una lista
     */
    public List<SalesDetail> getAll(){
        return salesDetailRepository.findAll();
    }

    public List<SalesDetail> getBySale(Long idSale){
        return salesDetailRepository.findBySaleIdSale(idSale);
    }
}
