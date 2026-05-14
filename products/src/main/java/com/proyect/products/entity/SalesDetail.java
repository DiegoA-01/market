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
    
    /**
     * Entidad que representa un detalle de venta en el sistema. Contiene los campos necesarios para definir un detalle de venta, como el ID del detalle de venta, la cantidad de productos vendidos, el precio unitario, la venta a la que pertenece el detalle, el usuario que realizó la venta y el producto vendido. Esta entidad se utiliza para mapear la información de los detalles de venta en la base de datos y para realizar operaciones relacionadas con los detalles de venta en el sistema.
     */
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
