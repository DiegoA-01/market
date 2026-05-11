package com.proyect.products.dto.ProductsRequest;

import com.proyect.products.enums.Rol;

import lombok.Data;

@Data
public class RegisterRequestDTO {
       
    
    private String name;
    private String email;
    private String password;
    private String phone;
    private Rol rol;
}
