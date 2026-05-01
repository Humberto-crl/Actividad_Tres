package com.humbertoperez.proyectoTienda.service;

import com.humbertoperez.proyectoTienda.entity.Clientes;
import com.humbertoperez.proyectoTienda.exception.ResourceNotFoundException;
import com.humbertoperez.proyectoTienda.repository.ClientesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServiceImpl implements ClientesService{

    @Autowired
    private ClientesRepo clientesRepo;


    @Override
    public List<Clientes> listar() {
        return clientesRepo.findAll();
    }

    @Override
    public Clientes crear(Clientes cliente) {
        return clientesRepo.save(cliente);
    }

    @Override
    public Clientes actualizar(Integer id, Clientes clientes) {
        Clientes existiendo = buscarPorId(id);

        clientes.setNombreCliente(clientes.getNombreCliente());
        clientes.setApellidoCliente(clientes.getApellidoCliente());
        clientes.setDireccion(clientes.getDireccion());
        clientes.setEstado(clientes.getEstado());

        return clientesRepo.save(existiendo);
    }

    @Override
    public Clientes buscarPorId(Integer id) {
        return clientesRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No encontrado: " + id));
    }

    @Override
    public void eliminar(Integer id) {
        if (!clientesRepo.existsById(id)) {
            throw new ResourceNotFoundException("No encontrado: " +id);
        }
        clientesRepo.deleteById(id);
    }
}
