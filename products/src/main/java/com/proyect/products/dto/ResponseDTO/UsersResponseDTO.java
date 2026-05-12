package com.proyect.products.dto.ResponseDTO;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import lombok.Setter;

@Data
@Setter
@Getter
@Builder

public class UsersResponseDTO {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String rol;
    
}
