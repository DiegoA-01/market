package com.proyect.products.dto.ProductsResponseDTO;

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
public class usersResponseDTO {
  
  private Long userId;
  private String name;
  private String userName;
  private String Password;
  private String rol;

}
