package com.proyect.products.dto.ResponseDTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesDetailResponseDTO {

    private Long productId;

    private String nombre;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subtotal;
    
}
