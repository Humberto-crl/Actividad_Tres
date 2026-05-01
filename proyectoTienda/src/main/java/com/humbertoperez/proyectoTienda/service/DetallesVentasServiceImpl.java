package com.humbertoperez.proyectoTienda.service;

import com.humbertoperez.proyectoTienda.entity.DetallesVenta;
import com.humbertoperez.proyectoTienda.entity.Producto;
import com.humbertoperez.proyectoTienda.entity.Venta;
import com.humbertoperez.proyectoTienda.exception.ResourceNotFoundException;
import com.humbertoperez.proyectoTienda.repository.DetallesVentaRepo;
import com.humbertoperez.proyectoTienda.repository.ProductoRepo;
import com.humbertoperez.proyectoTienda.repository.VentasRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallesVentasServiceImpl implements DetallesVentasService{

    private final DetallesVentaRepo detallesVentaRepo;
    private final ProductoRepo productoRepo;
    private final VentasRepo ventasRepo;

    public DetallesVentasServiceImpl(DetallesVentaRepo detallesVentaRepo, ProductoRepo productoRepo, VentasRepo ventasRepo) {
        this.detallesVentaRepo = detallesVentaRepo;
        this.productoRepo = productoRepo;
        this.ventasRepo = ventasRepo;
    }

    @Override
    public List<DetallesVenta> listar() {
        return detallesVentaRepo.findAll();
    }

    @Override
    public DetallesVenta crear(DetallesVenta detallesVenta) {
        Producto producto = productoRepo.findById(detallesVenta.getProducto().getCodigoProducto()).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        Venta venta= ventasRepo.findById(detallesVenta.getVenta().getCodigoVenta()).orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada"));

        detallesVenta.setProducto(producto);
        detallesVenta.setVenta(venta);

        return detallesVentaRepo.save(detallesVenta);
    }

    @Override
    public DetallesVenta actualizar(Integer id, DetallesVenta detalleVenta) {
        DetallesVenta actuDetalle = buscarPorId(id);

        detalleVenta.setCantidad(actuDetalle.getCantidad());
        detalleVenta.setTotal(actuDetalle.getTotal());
        detalleVenta.setTotal(actuDetalle.getTotal());

        Producto producto = productoRepo.findById(detalleVenta.getProducto().getCodigoProducto()).orElse(actuDetalle.getProducto());
        Venta venta = ventasRepo.findById(detalleVenta.getVenta().getCodigoVenta()).orElse(actuDetalle.getVenta());

        actuDetalle.setProducto(producto);
        actuDetalle.setVenta(venta);

        return detallesVentaRepo.save(detalleVenta);
    }

    @Override
    public DetallesVenta buscarPorId(Integer id) {
        return detallesVentaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada" + id));
    }

    @Override
    public void eliminar(Integer id) {
        if (!detallesVentaRepo.existsById(id)) {
            throw new ResourceNotFoundException("Venta no encontrada" + id);
        }
        detallesVentaRepo.deleteById(id);
    }
}
