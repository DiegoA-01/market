package com.proyect.products.dto.ProductsRequest;

import com.proyect.products.enums.Rol;

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
    private Rol rol;
}
