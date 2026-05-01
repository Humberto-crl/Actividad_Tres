package com.humbertoperez.proyectoTienda.service;

import com.humbertoperez.proyectoTienda.entity.Venta;

import java.util.List;

public interface VentasService {

    List<Venta> listar();
    Venta crear(Venta venta);
    Venta actualizar(Integer id, Venta venta);
    Venta buscarPorId(Integer id);
    void eliminar(Integer id);
}
