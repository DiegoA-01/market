package com.proyect.products.dto.ProductsResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUsersResponseDTO {
    /**
     * DTO para la respuesta de eliminación de un usuario. Contiene un mensaje que indica el resultado de la operación de eliminación, como si el usuario fue eliminado exitosamente o si ocurrió algún error durante el proceso. Este DTO se utiliza en las operaciones de eliminación de usuarios en el sistema.
     */
    private String message;
}
