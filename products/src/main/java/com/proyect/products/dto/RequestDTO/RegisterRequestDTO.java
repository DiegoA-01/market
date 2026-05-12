package com.proyect.products.dto.RequestDTO;

import lombok.Data;


@Data
public class RegisterRequestDTO {
    
    private String email;
    private String password;
    private String rol;
    private String name;
    private String phone;
}
