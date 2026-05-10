package com.proyect.products.dto.ProductsRequest;

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
public class usersRequestDTO {
  
  private String name;
  private String userName;
  private String password;
  private String rol;
}
