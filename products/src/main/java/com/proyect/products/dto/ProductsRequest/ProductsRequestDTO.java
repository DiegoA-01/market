package com.proyect.products.dto.ProductsRequest;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre es obligatorio")
    @Size( max = 100)
    private String name;

    @NotBlank(message = "Debete tener una descripcion el producto")
    @Size(max = 100)
    private String description;

    @NotBlank(message = "debe ingresar el precio.")
    private double price;

    @NotBlank(message = "El stock no puede ir vacio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;
}
