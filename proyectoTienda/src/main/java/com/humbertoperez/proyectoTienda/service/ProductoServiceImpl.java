package com.humbertoperez.proyectoTienda.service;

import com.humbertoperez.proyectoTienda.entity.Producto;
import com.humbertoperez.proyectoTienda.exception.ResourceNotFoundException;
import com.humbertoperez.proyectoTienda.repository.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepo productoRepo;

    @Override
    public List<Producto> listar() {
        return productoRepo.findAll();
    }

    @Override
    public Producto crear(Producto producto) {
        return productoRepo.save(producto);
    }

    @Override
    public Producto actualizar(Integer id, Producto producto) {
        Producto existir = buscarPorId(id);

        producto.setNombreProducto(producto.getNombreProducto());
        producto.setPrecio(producto.getPrecio());
        producto.setStock(producto.getStock());
        producto.setEstado(producto.getEstado());

        return productoRepo.save(existir);
    }

    @Override
    public Producto buscarPorId(Integer id) {
        return productoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + id));
    }

    @Override
    public void eliminar(Integer id) {
        if (!productoRepo.existsById(id)) {
            throw new ResourceNotFoundException("No encontrado: " + id);
        }
        productoRepo.deleteById(id);
    }
}
