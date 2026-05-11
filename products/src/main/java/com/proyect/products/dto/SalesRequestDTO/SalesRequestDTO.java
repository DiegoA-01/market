package com.proyect.products.dto.SalesRequestDTO;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SalesRequestDTO {
    @NotNull
    private LocalDateTime date;

    @NotNull
    @DecimalMin("0.0") // No acepta precios negativos o en cero
    private BigDecimal finalPrice;

    @NotNull
    private Long userId;
}
