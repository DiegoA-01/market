package com.proyect.products.dto.ProductsRequest;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesRequestDTO {

    private Integer cantidad;
    
    private Long userId;

    private Long productId;

    private BigDecimal subtotal;

    private BigDecimal total;
}
