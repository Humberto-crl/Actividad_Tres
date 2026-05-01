package com.humbertoperez.proyectoTienda.controller;

import com.humbertoperez.proyectoTienda.entity.DetallesVenta;
import com.humbertoperez.proyectoTienda.service.DetallesVentasService;
import com.humbertoperez.proyectoTienda.service.ProductoService;
import com.humbertoperez.proyectoTienda.service.VentasService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/detallesventa")
@Validated
public class DetallesVentaController {

    private final DetallesVentasService detallesVentasService;
    private final ProductoService productoService;
    private final VentasService ventasService;

    public DetallesVentaController(DetallesVentasService detallesVentasService, ProductoService productoService, VentasService ventasService) {
        this.detallesVentasService = detallesVentasService;
        this.productoService = productoService;
        this.ventasService = ventasService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("detalles", detallesVentasService.listar());
        return "detallesventa";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("detalle", new DetallesVenta());
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("ventas", detallesVentasService.listar());
        model.addAttribute("modoEdicion", false);
        return "detalle-form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("detalle") DetallesVenta detalle,
                          BindingResult result,
                          Model model){

        if(result.hasErrors()){
            model.addAttribute("productos", productoService.listar());
            model.addAttribute("ventas", detallesVentasService.listar());
            model.addAttribute("modoEdicion", false);
            return "detalle-form";
        }

        detallesVentasService.crear(detalle);
        return "redirect:/detallesventa";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable @Min(1) Integer id, Model model){

        DetallesVenta detalle = detallesVentasService.listar().stream()
                .filter(d -> d.getCodigoDetalleVenta().equals(id))
                .findFirst()
                .orElseThrow();

        model.addAttribute("detalle", detalle);
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("ventas", detallesVentasService.listar());
        model.addAttribute("modoEdicion", true);
        return "detalle-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable @Min(1) Integer id){
        detallesVentasService.eliminar(id);
        return "redirect:/detallesventa";
    }
}
