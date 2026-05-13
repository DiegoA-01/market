package com.proyect.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesDetailRequest {
    private Long idSale;
    private Long userId;
    private Long productId;
    private Integer cantidad;
}
