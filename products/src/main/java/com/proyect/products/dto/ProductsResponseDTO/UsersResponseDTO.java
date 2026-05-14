package com.proyect.products.dto.ProductsResponseDTO;

import com.proyect.products.enums.Rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsersResponseDTO {
    /**
     * DTO para la respuesta de usuarios. Contiene los campos necesarios para representar un usuario, como el ID del usuario, el nombre, el correo electrónico, la contraseña y el número de teléfono. Este DTO se utiliza en las operaciones de consulta de usuarios en el sistema, permitiendo que la información del usuario sea enviada al cliente de manera estructurada y clara.
     */
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Rol rol;
}
