package com.humbertoperez.proyectoTienda.repository;

import com.humbertoperez.proyectoTienda.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    List<Producto> findByNombreProductoContainingIgnoreCase(String nombreProducto);
    List<Producto> findByStockGreaterThan(int stock);
    List<Producto> findByEstadoTrue();
    List<Producto> findByEstadoFalse();
}
