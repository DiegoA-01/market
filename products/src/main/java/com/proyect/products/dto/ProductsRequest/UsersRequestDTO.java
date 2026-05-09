package com.proyect.products.dto.ProductsRequest;
import com.proyect.products.enums.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersRequestDTO {
    
    @NotBlank(message = "El nombre es obligatorio. ")
    @Column(name = "name")
    private String name;

    @Email(message = "Correo inválido")
    @NotBlank(message = "Email obligatorio. ")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Contraseña es obligatoria")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Telelfono es obligatorio.")
    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Rol rol;

}
