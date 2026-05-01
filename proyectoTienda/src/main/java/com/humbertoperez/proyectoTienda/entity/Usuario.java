package com.humbertoperez.proyectoTienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_usuario")
    private Integer codigoUsuario;

    @NotBlank(message = "El username es obligatorio.")
    @Size(min = 4, max = 45)
    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 4, max = 45)
    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @NotBlank(message = "El email es obligatorio.")
    @Email
    @Size(max = 60)
    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @NotBlank(message = "El rol es obligatorio.")
    @Column(name = "rol", nullable = false)
    private String rol;

    @NotNull(message = "El estado es obligatorio.")
    @Column(name = "estado", nullable = false)
    private Boolean estado;
}


