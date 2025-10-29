package com.avistamientos_animales.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.avistamientos_animales.demo.model.Especie;
import com.avistamientos_animales.demo.service.EspecieService;

@Controller
@RequestMapping("/especies")
public class EspecieController {
    
    @Autowired
    private EspecieService especieService;

    // Mostrar lista de especies
    @GetMapping("/consultar")
    public String listar(Model model) {
        model.addAttribute("especies", especieService.listar());
        return "especie-consultar";
    }
    // Mostrar formulario de agregar
    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("especie", new Especie());
        return "especie-agregar";
    }
    // Guardar especie
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Especie especie) {
        especieService.guardar(especie);
        return "redirect:/especies";
    }
    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        especieService.eliminar(id);
        return "redirect:/especies/consultar";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable String id, Model model) {
        Especie especie = especieService.obtenerPorId(id);
        if (especie != null) {
            model.addAttribute("especie", especie);
            return "especie-editar";
        } else {
            // Redirige si no encuentra la especie
            return "redirect:/especies";
        }
    }
}
