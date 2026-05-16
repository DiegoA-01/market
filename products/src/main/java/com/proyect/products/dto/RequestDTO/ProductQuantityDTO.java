package com.proyect.products.dto.RequestDTO;


import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductQuantityDTO{
    
    private Long productId;

    private Integer cantidad;
}