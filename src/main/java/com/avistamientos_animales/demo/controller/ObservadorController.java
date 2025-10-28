package com.avistamientos_animales.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.avistamientos_animales.demo.model.Observador;
import com.avistamientos_animales.demo.service.ObservadorService;

@Controller
@RequestMapping("/observadores")
public class ObservadorController {
     @Autowired
    private ObservadorService observadorService;

    //Mostrar lista de observadores
    @GetMapping("/consultar")
    public String listar(Model model) {
        model.addAttribute("observadores", observadorService.listar());
        return "observador-consultar";
    }

    // Mostrar formulario de agregar
    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("observador", new Observador());
        return "observador-agregar";
    }

    // Guardar observador
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Observador observador) {
        observadorService.guardar(observador);
        return "redirect:/observadores";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        observadorService.eliminar(id);
        return "redirect:/observadores/consultar";
    }
}
