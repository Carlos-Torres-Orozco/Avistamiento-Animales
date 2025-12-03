package com.avistamientos_animales.demo.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.avistamientos_animales.demo.model.Animal;
import com.avistamientos_animales.demo.service.AnimalService;
import com.avistamientos_animales.demo.service.EspecieService;
import com.avistamientos_animales.demo.service.ObservadorService;

@Controller
@RequestMapping("/animales")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private EspecieService especieService;

    @Autowired
    private ObservadorService observadorService;

    // Página principal de gestión de animales
    @GetMapping("")
    public String gestionAnimales(Model model) {
        // Ya no necesitamos Principal aquí, el atributo "usuario" viene por ControllerAdvice
        return "animales"; // nombre del template Thymeleaf: animales.html
    }

    @GetMapping("/consultar")
    public String listar(Model model) {
        model.addAttribute("animales", animalService.listar());
        return "animal-consultar";
    }

    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("especies", especieService.listar());
        return "animal-agregar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Animal animal) {
        animalService.guardar(animal);
        return "redirect:/animales";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        animalService.eliminar(id);
        return "redirect:/animales/consultar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable String id, Model model) {
        Animal animal = animalService.obtenerPorId(id);
        if (animal != null) {
            model.addAttribute("animal", animal);
            model.addAttribute("especies", especieService.listar());
            return "animal-editar";
        } else {
            return "redirect:/animales/consultar";
        }
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Animal animal) {
        animalService.guardar(animal);
        return "redirect:/animales/consultar";
    }
}
    