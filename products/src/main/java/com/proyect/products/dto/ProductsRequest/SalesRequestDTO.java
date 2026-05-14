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

    /**
     * DTO para la creación de una venta. Contiene los campos necesarios para definir una venta, como la cantidad de productos vendidos, el ID del usuario que realiza la venta, el ID del producto vendido, el subtotal y el total de la venta. Este DTO se utiliza en las operaciones de creación de ventas en el sistema.
     */
    private Integer cantidad;
    
    private Long userId;

    private Long productId;

    private BigDecimal subtotal;

    private BigDecimal total;
}
