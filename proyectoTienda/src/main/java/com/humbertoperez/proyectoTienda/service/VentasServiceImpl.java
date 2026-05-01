package com.humbertoperez.proyectoTienda.service;

import com.humbertoperez.proyectoTienda.entity.Clientes;
import com.humbertoperez.proyectoTienda.entity.Usuario;
import com.humbertoperez.proyectoTienda.entity.Venta;
import com.humbertoperez.proyectoTienda.exception.ResourceNotFoundException;
import com.humbertoperez.proyectoTienda.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasServiceImpl implements VentasService{

    private final VentasRepo ventasRepo;
    private final UsuarioRepo usuarioRepo;
    private final ClientesRepo clientesRepo;

    public VentasServiceImpl(VentasRepo ventasRepo, UsuarioRepo usuarioRepo, ClientesRepo clientesRepo) {
        this.ventasRepo = ventasRepo;
        this.usuarioRepo = usuarioRepo;
        this.clientesRepo = clientesRepo;
    }

    @Override
    public List<Venta> listar() {
        return ventasRepo.findAll();
    }

    @Override
    public Venta crear(Venta venta) {
        Clientes clientes = clientesRepo.findById(venta.getClientes().getDpiCliente()).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        Usuario usuario = usuarioRepo.findById(venta.getUsuario().getCodigoUsuario()).orElseThrow(() -> new ResourceNotFoundException("Usuario no encotrado"));

        venta.setClientes(clientes);
        venta.setUsuario(usuario);

        return ventasRepo.save(venta);
    }

    @Override
    public Venta actualizar(Integer id, Venta venta) {
        Venta actu = buscarPorId(id);

        venta.setFechaVenta(venta.getFechaVenta());
        venta.setTotal(venta.getTotal());
        venta.setEstado(venta.getEstado());

        Clientes clientes = clientesRepo.findById(venta.getClientes().getDpiCliente()).orElse(actu.getClientes());
        Usuario usuario = usuarioRepo.findById(venta.getUsuario().getCodigoUsuario()).orElse(actu.getUsuario());

        actu.setClientes(clientes);
        actu.setUsuario(usuario);

        return ventasRepo.save(actu);
    }

    @Override
    public Venta buscarPorId(Integer id) {
        return ventasRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada: " + id));
    }

    @Override
    public void eliminar(Integer id) {
        if (!ventasRepo.existsById(id)) {
            throw new ResourceNotFoundException("No encontrado: " + id);
        }
        ventasRepo.deleteById(id);
    }
}
