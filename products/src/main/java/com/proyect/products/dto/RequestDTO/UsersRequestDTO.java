package com.proyect.products.dto.RequestDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersRequestDTO {

    private String name;
    private String email;
    private String password;
    private String phone;
    private String rol;


}
