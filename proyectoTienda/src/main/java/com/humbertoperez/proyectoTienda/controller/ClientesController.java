package com.humbertoperez.proyectoTienda.controller;

import com.humbertoperez.proyectoTienda.entity.Clientes;
import com.humbertoperez.proyectoTienda.service.ClientesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
@Validated
public class ClientesController {

    private final ClientesService service;

    public ClientesController(ClientesService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("clientes", service.listar());
        return "clientes";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("cliente", new Clientes());
        model.addAttribute("modoEdicion", false);
        return "cliente-form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("cliente") Clientes cliente,
                          BindingResult result,
                          Model model){

        if(result.hasErrors()){
            model.addAttribute("modoEdicion", false);
            return "cliente-form";
        }

        service.crear(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable @Min(1) Integer id, Model model){
        Clientes cliente = service.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        model.addAttribute("modoEdicion", true);
        return "cliente-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable @Min(1) Integer id,
                             @Valid @ModelAttribute("cliente") Clientes cliente,
                             BindingResult result,
                             Model model){

        if(result.hasErrors()){
            model.addAttribute("modoEdicion", true);
            return "cliente-form";
        }

        cliente.setDpiCliente(id);
        service.actualizar(id, cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable @Min(1) Integer id){
        service.eliminar(id);
        return "redirect:/clientes";
    }
}
