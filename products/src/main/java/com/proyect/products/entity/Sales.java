package com.proyect.products.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long saleId;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    /**
     * BigDecimal procesar gran precision de numeros de coma flotante
     */
    @Column (name = "final_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal finalPrice;
    
    /**
     * @ManyToOne relacion M:1 muchas ventas pertenecen a un usuario
     * @JoinColumn Nombre Foreign key en la tabla
     * fetch = FetchType.LAZY dice cuando cargar los datos del usuario desde la base de datos
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;                
}