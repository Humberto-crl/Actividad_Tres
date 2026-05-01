package com.humbertoperez.proyectoTienda.repository;

import com.humbertoperez.proyectoTienda.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface    ClientesRepo extends JpaRepository<Clientes, Integer> {

    List<Clientes> findByNombreClienteContainingIgnoreCase(String nombreCliente);
    List<Clientes> findByApellidoClienteContainingIgnoreCase(String apellidoCliente);
    List<Clientes> findByEstadoTrue();
    List<Clientes> findByEstadoFalse();
}
