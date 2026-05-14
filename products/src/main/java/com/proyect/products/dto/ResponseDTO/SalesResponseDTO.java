package com.proyect.products.dto.ResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesResponseDTO {

    private Long idSale;

    private LocalDateTime fecha;

    private BigDecimal subtotal;

    private BigDecimal total;

    private String userName;

    private String userRole;

    private String productName;

    private Integer cantidad;
}
