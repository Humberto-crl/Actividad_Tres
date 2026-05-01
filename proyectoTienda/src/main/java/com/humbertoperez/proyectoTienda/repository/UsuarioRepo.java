package com.humbertoperez.proyectoTienda.repository;

import com.humbertoperez.proyectoTienda.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsernameAndEstadoTrue(String username);
    List<Usuario> findByRol(String rol);
    List<Usuario> findByEstadoTrue();
    List<Usuario> findByEstadoFalse();
}
