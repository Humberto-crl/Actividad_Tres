package com.humbertoperez.proyectoTienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "detalles_venta")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DetallesVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_venta")
    private Integer codigoDetalleVenta;

    @NotNull(message = "La cantidad es obligatoria.")
    @Min(1)
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull(message = "El subtotal es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    @NotNull(message = "El total es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "total", nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "codigo_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "codigo_venta", nullable = false)
    private Venta venta;
}
