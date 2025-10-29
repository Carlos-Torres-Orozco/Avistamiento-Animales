package com.avistamientos_animales.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.avistamientos_animales.demo.model.Ubicacion;
import com.avistamientos_animales.demo.service.UbicacionService;

@Controller
@RequestMapping("/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    //Mostrar lista de ubicaciones
    @GetMapping("/consultar")
    public String listar(Model model) {
        model.addAttribute("ubicaciones", ubicacionService.listar());
        return "ubicacion-consultar";
    }

    // Mostrar formulario de agregar
    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("ubicacion", new Ubicacion());
        return "ubicacion-agregar";
    }

    // Guardar ubicacion
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Ubicacion ubicacion) {
        ubicacionService.guardar(ubicacion);
        return "redirect:/ubicaciones";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        ubicacionService.eliminar(id);
        return "redirect:/ubicaciones/consultar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable String id, Model model) {
        Ubicacion ubicacion = ubicacionService.obtenerPorId(id);
        if (ubicacion != null) {
            model.addAttribute("ubicacion", ubicacion);
            return "ubicacion-editar";
        } else {
            // Redirige si no encuentra la ubicacion
            return "redirect:/ubicaciones";
        }
    }

    // 🔹 Guardar cambios de la ubicacio
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("ubicacion") Ubicacion ubicacion) {
        ubicacionService.actualizar(ubicacion);
        return "redirect:/ubicaciones/consultar";
    }
}
