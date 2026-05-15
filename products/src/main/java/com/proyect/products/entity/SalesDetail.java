package com.proyect.products.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_detail")
public class SalesDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale_detail")
    private Long salesDetailId;

    @ManyToOne
    @JoinColumn(name = "id_sale", nullable = false)
    private Sales sale;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @Column( name = " cantidad", nullable = false)
    private Integer cantidad;

    @Column( name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @Column ( name = "subtotal", nullable = false)
    private BigDecimal subtotal;
    
}
