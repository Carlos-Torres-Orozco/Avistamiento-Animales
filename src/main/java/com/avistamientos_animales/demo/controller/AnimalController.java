package com.avistamientos_animales.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avistamientos_animales.demo.model.Animal;
import com.avistamientos_animales.demo.service.AnimalService;
import com.avistamientos_animales.demo.service.EspecieService;


@Controller
@RequestMapping("/animales")
public class AnimalController {
    @Autowired
    private AnimalService animalService;
    @Autowired
    private EspecieService especieService;

    // Mostrar lista de animales
    @GetMapping("/consultar")
    public String listar(Model model) {
        model.addAttribute("animales", animalService.listar());
        return "animal-consultar";
    }
    // Mostrar formulario de agregar
    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("especies", especieService.listar());
        return "animal-agregar";
    }
    // Guardar animal
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Animal animal) {
        animalService.guardar(animal);
        return "redirect:/animales";
    }
    // Eliminar
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
