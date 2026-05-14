package com.proyect.products.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Products {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @NotBlank(message = "El nombre es obligatorio")
    @Size( max = 100)
    @Column(name = "name", unique = true)
    private String name;

    @NotBlank(message = "Debete tener una descripcion el producto")
    @Column(name = "description")
    @Size(max = 100)
    private String description;

    @NotNull(message = "debe ingresar el precio.")
    @Column(name = "price", precision = 19, scale = 2)
    private BigDecimal price;

    @NotNull(message = "El stock no puede ir vacio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(name = "stock")
    private int stock;
}
