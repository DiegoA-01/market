package com.proyect.products.dto.SalesResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesResponseDTO {

    private Long saleId;
    private LocalDateTime date;
    private BigDecimal finalPrice;
    private Long userId;
    private String userName;
}
