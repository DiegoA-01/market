package com.proyect.products.dto.ProductsResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsResponseDTO {

    private Long productId;
    private String name;
    private String description;
    private double price;
    private int stock;
}
