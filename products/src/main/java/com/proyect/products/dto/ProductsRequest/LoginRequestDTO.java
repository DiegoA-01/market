package com.proyect.products.dto.ProductsRequest;

import lombok.Data;

@Data
public class LoginRequestDTO {
    /**
     * DTO para el inicio de sesión de un usuario. Contiene los campos necesarios para que un usuario pueda autenticarse en el sistema, como el correo electrónico y la contraseña.
     */
    private String email;
    private String password;
}
