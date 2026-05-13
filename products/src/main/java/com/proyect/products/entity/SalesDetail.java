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

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @ManyToOne
    @JoinColumn(name = "id_sale")
    private Sales sale;

    /**
     * Muchos detalles pueden pertenecer a un producto
     */
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users user;

    /**
     * Muchos detalles
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    
}
