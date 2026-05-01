package com.humbertoperez.proyectoTienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "venta")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private Integer codigoVenta;

    @NotNull(message = "La fecha es obligatoria.")
    @Column(name = "fecha_venta", nullable = false)
    private LocalDate fechaVenta;

    @NotNull(message = "El total es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @NotNull(message = "El estado es obligatorio.")
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "dpi_cliente", nullable = false)
    private Clientes clientes;

    @ManyToOne
    @JoinColumn(name = "codigo_usuario", nullable = false)
    private Usuario usuario;
}
