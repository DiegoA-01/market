package com.proyect.products.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale")
    private Long idSale;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Users users;

    @Column(name = "subtotal")
    private BigDecimal subTotal;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    /**
     * cascade = ALL - guarda automáticamente los detalles de la venta al guardar la venta principal
     * 
     * orphanRemoval = true - Si un detalle se quita de la lista, se borra de la base de datos 
     */
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesDetail> detalles = new ArrayList<>();

}
