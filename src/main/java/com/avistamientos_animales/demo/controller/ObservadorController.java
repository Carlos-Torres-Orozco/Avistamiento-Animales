package com.avistamientos_animales.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
        private PasswordEncoder passwordEncoder;

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

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Observador observador) {

        String encrypted = passwordEncoder.encode(observador.getContrasena());
        observador.setContrasena(encrypted);

        observadorService.guardar(observador);

        return "redirect:/login";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        observadorService.eliminar(id);
        return "redirect:/observadores/consultar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable String id, Model model) {
        Observador observador = observadorService.obtenerPorId(id);
        if (observador != null) {
            model.addAttribute("observador", observador);
            return "observador-editar";
        } else {
            // Redirige si no encuentra el observador
            return "redirect:/observadores";
        }
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("observador") Observador observador) {
        observadorService.actualizar(observador);
        return "redirect:/observadores";
    }

    @GetMapping("/crear-cuenta")
    public String mostrarFormularioLogIn(Model model) {
        model.addAttribute("observador", new Observador());
        return "observadores-creacion";
    }
}
