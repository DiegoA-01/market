package com.proyect.products.dto.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO extends MessageResponseDTO {

    private String jwt;
    private String message;
}
