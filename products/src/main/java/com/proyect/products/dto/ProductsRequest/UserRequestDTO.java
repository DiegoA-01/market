package com.proyect.products.dto.ProductsRequest;
import com.proyect.products.enums.Role;

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
public class UserRequestDTO {
  
  private String name;
  private String email;
  private String password;
  private String phone;
  private Role role;
}
