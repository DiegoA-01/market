package com.proyect.products.dto.ProductsResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshResponseDTO {
    /**
     * DTO para la respuesta de actualización de token. Contiene un token JWT que se genera al actualizar el token de autenticación, así como un mensaje que indica el resultado de la operación de actualización, como si la actualización fue exitosa o si ocurrió algún error durante el proceso. Este DTO se utiliza en las operaciones de actualización de token en el sistema.
     */
    private String jwt;
    private String message;
}
