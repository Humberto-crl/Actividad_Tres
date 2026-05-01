package com.humbertoperez.proyectoTienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dpi_cliente")
    private Integer dpiCliente;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 4, max = 50)
    @Column(name = "nombre_cliente", nullable = false, length = 50)
    private String nombreCliente;

    @NotBlank(message = "El apellido es obligatorio.")
    @Size(min = 4, max = 50)
    @Column(name = "apellido_cliente", nullable = false, length = 50)
    private String apellidoCliente;

    @NotBlank(message = "La dirección es obligatoria.")
    @Size(max = 100)
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @NotNull(message = "El estado es obligatorio.")
    @Column(name = "estado", nullable = false)
    private Boolean estado;
}
