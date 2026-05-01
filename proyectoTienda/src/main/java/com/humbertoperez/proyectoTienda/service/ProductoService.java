package com.humbertoperez.proyectoTienda.service;

import com.humbertoperez.proyectoTienda.entity.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoService {

    List<Producto> listar();
    Producto crear(Producto producto);
    Producto actualizar(Integer id, Producto producto);
    Producto buscarPorId(Integer id);
    void eliminar(Integer id);
}
