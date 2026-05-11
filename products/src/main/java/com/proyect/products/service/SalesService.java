package com.proyect.products.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.proyect.products.dto.SalesRequestDTO.SalesRequestDTO;
import com.proyect.products.dto.SalesResponseDTO.SalesResponseDTO;
import com.proyect.products.entity.Sales;
import com.proyect.products.entity.User;
import com.proyect.products.repository.SalesRepository;
import com.proyect.products.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesService {

    SalesRepository salesRepository;
    UserRepository usersRepository;

    /**
     * Metodo para crear una venta
     * 
     * @param requestDTO
     * @return
     */

    public SalesResponseDTO createSale(SalesRequestDTO requestDTO) {
        User user = usersRepository.findById(requestDTO.getUserId())
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        Sales sales = new Sales();
        sales.setDate(requestDTO.getDate());
        sales.setFinalPrice(requestDTO.getFinalPrice());
        sales.setUser(user);

        Sales saveSales = salesRepository.save(sales);
        return toResponse(saveSales);
    }

    /**
     * Metodo para listar todas las ventas
     * @return
     */
    public List<SalesResponseDTO> listSales() {
        return salesRepository.findAll().stream().map(this::toResponse).toList();
    }

    /**
     * Metodo para buscar venta por id
     * 
     * @param saleId
     * @return
     */
    public SalesResponseDTO showId(Long saleId){
        Sales sales = salesRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException ("Venta no encontrada."));
        return toResponse(sales);
    }

    /**
     * Metodo para actualizar una venta
     * @param saleId
     * @param requestDTO
     * @return
     */
    public SalesResponseDTO putSale(Long saleId, SalesRequestDTO requestDTO){
        Sales sales = salesRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada."));
        
        User user = usersRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        sales.setDate(requestDTO.getDate());
        sales.setFinalPrice(requestDTO.getFinalPrice());
        sales.setUser(user);

        Sales saveSales = salesRepository.save(sales);
        return toResponse(saveSales);
    }

    /**
     * Metodo eliminar venta por id
     * @param saleId
     */
    public void deleteSaleId(Long saleId) {
        Sales sales = salesRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        salesRepository.delete(sales);
    }

    /**
     * Respuesta para reducir codigo
     * @param sales
     * @return
     */
    public SalesResponseDTO toResponse(Sales sales) {
        return SalesResponseDTO.builder()
                .saleId(sales.getSaleId())
                .date(sales.getDate())
                .finalPrice(sales.getFinalPrice())
                .userId(sales.getUser().getIdUser())
                .userName(sales.getUser().getName())
                .build();
    }

}

