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
    
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Rol rol;
}
