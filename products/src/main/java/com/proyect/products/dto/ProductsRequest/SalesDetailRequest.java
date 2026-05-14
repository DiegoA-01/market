package com.proyect.products.dto.ProductsRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesDetailRequest {
    /**
     * DTO para la creación de un detalle de venta. Contiene los campos necesarios para definir un detalle de venta, como el ID de la venta a la que pertenece el detalle, el ID del usuario que realizó la venta, el ID del producto vendido y la cantidad de productos vendidos. Este DTO se utiliza en las operaciones de creación de detalles de venta en el sistema.
     */
    private Long idSale;
    private Long userId;
    private Long productId;
    private Integer cantidad;
}
