package com.proyect.products.dto.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

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
    private BigDecimal price;
    private int stock;
}
