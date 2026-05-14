package com.proyect.products.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name = "sale")
public class Sales {
    
    /**
     * Entidad que representa una venta en el sistema. Contiene los campos necesarios para definir una venta, como el ID de la venta, la fecha de la venta, el subtotal, el total, la cantidad de productos vendidos, el usuario que realizó la venta y el producto vendido. Esta entidad se utiliza para mapear la información de las ventas en la base de datos y para realizar operaciones relacionadas con las ventas en el sistema.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale")
    private Long idSale;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "subtotal")
    private BigDecimal subTotal;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;


}
