package com.proyect.products.dto.ResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    private String userName;

    private BigDecimal subtotal;

    private BigDecimal total;

    private Integer cantidad;

    private List<SalesDetailResponseDTO> detalles;
}
