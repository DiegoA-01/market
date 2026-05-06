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
public class ProductsRequestDTO {

    private String name;
    private String description;
    private double price;
    private int stock;
}
