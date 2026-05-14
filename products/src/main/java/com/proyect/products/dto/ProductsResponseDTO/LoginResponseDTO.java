package com.proyect.products.dto.ProductsResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    /**
     * DTO para la respuesta del inicio de sesión de un usuario. Contiene un token JWT que se genera al autenticarse exitosamente, así como un mensaje que indica el resultado de la operación de inicio de sesión, como si el inicio de sesión fue exitoso o si ocurrió algún error durante el proceso. Este DTO se utiliza en las operaciones de inicio de sesión en el sistema.
     */
    private String jwt;
    private String message;
}
