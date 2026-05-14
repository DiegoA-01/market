package com.proyect.products.dto.ProductsResponseDTO;

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

    /**
     * DTO para la respuesta de productos. Contiene los campos necesarios para representar un producto, como el ID del producto, el nombre, la descripción, el precio y el stock disponible. Este DTO se utiliza en las operaciones de consulta de productos en el sistema, permitiendo que la información del producto sea enviada al cliente de manera estructurada y clara.
     */
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
}
