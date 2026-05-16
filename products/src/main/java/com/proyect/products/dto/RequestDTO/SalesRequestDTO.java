package com.proyect.products.dto.RequestDTO;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesRequestDTO {
    /**
     * Lista los productos con sus respectivas cantidades para realizar una venta. 
     */
    List<ProductQuantityDTO> products;
}
