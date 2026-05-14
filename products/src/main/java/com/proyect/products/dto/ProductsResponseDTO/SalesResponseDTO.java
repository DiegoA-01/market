package com.proyect.products.dto.ProductsResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesResponseDTO {

    /**
     * DTO para la respuesta de ventas. Contiene los campos necesarios para representar una venta, como el ID de la venta, la fecha de la venta, el subtotal, el total, el nombre del usuario que realizó la venta y el nombre del producto vendido. Este DTO se utiliza en las operaciones de consulta de ventas en el sistema, permitiendo que la información de la venta sea enviada al cliente de manera estructurada y clara.
     */
    private Long idSale;

    private LocalDateTime fecha;

    private BigDecimal subtotal;

    private BigDecimal total;

    private String userName;

    private String productName;
}
