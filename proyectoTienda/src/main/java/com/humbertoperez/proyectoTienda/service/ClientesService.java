package com.humbertoperez.proyectoTienda.service;

import com.humbertoperez.proyectoTienda.entity.Clientes;

import java.util.List;

public interface ClientesService {

    List<Clientes> listar();
    Clientes crear(Clientes cliente);
    Clientes actualizar(Integer id, Clientes clientes);
    Clientes buscarPorId(Integer id);
    void eliminar(Integer id);
}
