package com.proyect.products.dto.RequestDTO;

import com.proyect.products.enums.Rol;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    
    @NotBlank(message = "El nombre es obligatorio. ")
    private String name;

    @Email(message = "Correo inválido")
    @NotBlank(message = "Email obligatorio. ")
    private String email;

    @NotBlank(message = "Contraseña es obligatoria")
    private String password;

    @NotBlank(message = "Telelfono es obligatorio.")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Rol rol;

}
