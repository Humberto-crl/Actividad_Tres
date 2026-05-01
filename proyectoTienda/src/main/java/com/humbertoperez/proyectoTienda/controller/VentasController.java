package com.humbertoperez.proyectoTienda.controller;

import com.humbertoperez.proyectoTienda.entity.Venta;
import com.humbertoperez.proyectoTienda.service.ClientesService;
import com.humbertoperez.proyectoTienda.service.UsuarioService;
import com.humbertoperez.proyectoTienda.service.VentasService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ventas")
@Validated
public class VentasController {

    private final VentasService ventasService;
    private final ClientesService clientesService;
    private final UsuarioService usuarioService;

    public VentasController(VentasService service,
                           ClientesService clientesService,
                           UsuarioService usuarioService) {
        this.ventasService = service;
        this.clientesService = clientesService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("ventas", ventasService.listar());
        return "ventas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clientesService.listar());
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("modoEdicion", false);
        return "venta-form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("venta") Venta venta,
                          BindingResult result,
                          Model model){

        if(result.hasErrors()){
            model.addAttribute("clientes", clientesService.listar());
            model.addAttribute("usuarios", usuarioService.listar());
            model.addAttribute("modoEdicion", false);
            return "venta-form";
        }

        ventasService.crear(venta);
        return "redirect:/ventas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable @Min(1) Integer id, Model model){

        Venta venta = ventasService.buscarPorId(id);
        model.addAttribute("venta", venta);
        model.addAttribute("clientes", clientesService.listar());
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("modoEdicion", true);
        return "venta-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable @Min(1) Integer id,
                             @Valid @ModelAttribute("venta") Venta venta,
                             BindingResult result,
                             Model model){

        if(result.hasErrors()){
            model.addAttribute("clientes", clientesService.listar());
            model.addAttribute("usuarios", usuarioService.listar());
            model.addAttribute("modoEdicion", true);
            return "venta-form";
        }

        venta.setCodigoVenta(id);
        ventasService.actualizar(id, venta);
        return "redirect:/ventas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable @Min(1) Integer id){
        ventasService.eliminar(id);
        return "redirect:/ventas";
    }
}
