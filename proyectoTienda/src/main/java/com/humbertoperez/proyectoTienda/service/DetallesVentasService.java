package com.humbertoperez.proyectoTienda.service;

import com.humbertoperez.proyectoTienda.entity.DetallesVenta;

import java.util.List;

public interface DetallesVentasService {

    List<DetallesVenta> listar();
    DetallesVenta crear(DetallesVenta detallesVenta);
    DetallesVenta actualizar(Integer id, DetallesVenta detalleVenta);
    DetallesVenta buscarPorId(Integer id);
    void eliminar(Integer id);
}
