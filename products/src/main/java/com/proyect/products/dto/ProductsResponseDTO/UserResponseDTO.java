package com.proyect.products.dto.ProductsResponseDTO;
import com.proyect.products.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
  
  private Long userId;
  private String name;
  private String email;
  private String password;
  private String phone;
  private Role role;
}
