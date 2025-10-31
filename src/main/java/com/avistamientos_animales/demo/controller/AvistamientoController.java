package com.avistamientos_animales.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avistamientos_animales.demo.model.Avistamiento;
import com.avistamientos_animales.demo.service.AvistamientoService;
import com.avistamientos_animales.demo.service.AnimalService;
import com.avistamientos_animales.demo.service.UbicacionService;
import com.avistamientos_animales.demo.service.ObservadorService;

@Controller
@RequestMapping("/avistamientos")
public class AvistamientoController {
    @Autowired
    private AvistamientoService avistamientoService;
    @Autowired
    private AnimalService animalService;
    @Autowired
    private UbicacionService ubicacionService;
    @Autowired
    private ObservadorService observadorService;
    // Mostrar lista de avistamientos
    @GetMapping("/consultar")
    public String listar(Model model) {
        model.addAttribute("avistamientos", avistamientoService.listar());
        return "avistamiento-consultar";
    }
    // Mostrar formulario de agregar
    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("avistamiento", new Avistamiento());
        model.addAttribute("animales", animalService.listar());
        model.addAttribute("ubicaciones", ubicacionService.listar());
        model.addAttribute("observadores", observadorService.listar());
        return "avistamiento-agregar";
    }
    // Guardar avistamiento
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Avistamiento avistamiento) {
        // Asegura la relación bidireccional
        if (avistamiento.getMultimedias() != null) {
            avistamiento.getMultimedias().forEach(m -> m.setAvistamiento(avistamiento));
        }

        avistamientoService.guardar(avistamiento);
        return "redirect:/avistamientos/consultar";
    }
    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        avistamientoService.eliminar(id);
        return "redirect:/avistamientos/consultar";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable String id, Model model) {
        Avistamiento avistamiento = avistamientoService.obtenerPorId(id);
        if (avistamiento != null) {
            model.addAttribute("avistamiento", avistamiento);
            model.addAttribute("animales", animalService.listar());
            model.addAttribute("ubicaciones", ubicacionService.listar());
            model.addAttribute("observadores", observadorService.listar());
            return "avistamiento-editar";
        } else {
            return "redirect:/avistamientos/consultar";
        }
    }
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Avistamiento avistamiento) {
       if (avistamiento.getMultimedias() != null) {
        avistamiento.getMultimedias().forEach(m -> m.setAvistamiento(avistamiento));
        }

        avistamientoService.guardar(avistamiento);
        return "redirect:/avistamientos/consultar";
    }

    @GetMapping("/multimedia/{idAvistamiento}")
    public String verMultimedia(@PathVariable String idAvistamiento, Model model) {
        Avistamiento avistamiento = avistamientoService.obtenerPorId(idAvistamiento);
        
        if (avistamiento != null) {
            model.addAttribute("avistamiento", avistamiento);
            model.addAttribute("multimedias", avistamiento.getMultimedias());
            return "avistamiento-multimedia";
        } else {
            return "redirect:/avistamientos/consultar";
        }
    }
}
