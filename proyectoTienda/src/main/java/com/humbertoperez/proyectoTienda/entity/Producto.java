package com.humbertoperez.proyectoTienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
    private Integer codigoProducto;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 4, max = 60)
    @Column(name = "nombre_producto", nullable = false, length = 60)
    private String nombreProducto;

    @NotNull(message = "El precio es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "precio", nullable = false)
    private Double precio;

    @NotNull(message = "El stock es obligatorio.")
    @Min(0)
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @NotNull(message = "El estado es obligatorio.")
    @Column(name = "estado", nullable = false)
    private Boolean estado;
}
