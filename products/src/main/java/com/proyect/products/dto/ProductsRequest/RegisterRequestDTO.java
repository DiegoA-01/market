package com.proyect.products.dto.ProductsRequest;

import com.proyect.products.enums.Rol;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RegisterRequestDTO {
       
    /**
     * DTO para el registro de un nuevo usuario. Contiene los campos necesarios para que un nuevo usuario pueda registrarse en el sistema, como el nombre, el correo electrónico, la contraseña, el número de teléfono y el rol del usuario.
     */
    private String name;
    private String email;
    private String password;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Rol rol;
}
