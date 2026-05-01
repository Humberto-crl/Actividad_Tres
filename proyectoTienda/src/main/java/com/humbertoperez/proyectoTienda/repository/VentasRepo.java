package com.humbertoperez.proyectoTienda.repository;

import com.humbertoperez.proyectoTienda.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentasRepo extends JpaRepository<Venta, Integer> {

    List<Venta> findByFechaVentaBetween(LocalDate inicio, LocalDate fin);
    List<Venta> findByUsuarioCodigoUsuario(Integer codigoUsuario);
    List<Venta> findByFechaVenta(LocalDate fechaVenta);
    List<Venta> findByEstadoTrue();
    List<Venta> findByEstadoFalse();
}
