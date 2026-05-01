package com.humbertoperez.proyectoTienda.controller;

import com.humbertoperez.proyectoTienda.entity.Producto;
import com.humbertoperez.proyectoTienda.service.ProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
@Validated
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("productos", service.listar());
        return "productos";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("producto", new Producto());
        model.addAttribute("modoEdicion", false);
        return "producto-form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("producto") Producto producto,
                          BindingResult result,
                          Model model){

        if(result.hasErrors()){
            model.addAttribute("modoEdicion", false);
            return "producto-form";
        }

        service.crear(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable @Min(1) Integer id, Model model){

        Producto producto = service.buscarPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("modoEdicion", true);
        return "producto-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable @Min(1) Integer id,
                             @Valid @ModelAttribute("producto") Producto producto,
                             BindingResult result,
                             Model model){

        if(result.hasErrors()){
            model.addAttribute("modoEdicion", true);
            return "producto-form";
        }

        producto.setCodigoProducto(id);
        service.actualizar(id, producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable @Min(1) Integer id){
        service.eliminar(id);
        return "redirect:/productos";
    }
}
