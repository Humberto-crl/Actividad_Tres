package com.humbertoperez.proyectoTienda.repository;

import com.humbertoperez.proyectoTienda.entity.DetallesVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallesVentaRepo extends JpaRepository<DetallesVenta, Integer> {

    List<DetallesVenta> findByVentaCodigoVenta(Integer codigoVenta);
    List<DetallesVenta> findByProductoCodigoProducto(Integer codigoProducto);
}
