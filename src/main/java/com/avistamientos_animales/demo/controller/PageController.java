package com.avistamientos_animales.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String inicio() {
        return "index";  // Carga templates/index.html
    }

    @GetMapping("/gestion")
    public String gestion() {
        return "gestion";  // Carga templates/gestion.html
    }

    @GetMapping("/especies")
    public String especies() {
        return "especies";  // Carga templates/especies.html
    }

    @GetMapping("/animales")
    public String animales() {
        return "animales";  // Carga templates/animales.html
    }

    @GetMapping("/avistamientos")
    public String avistamientos() {
        return "avistamientos";  // Carga templates/avistamientos.html
    }

    @GetMapping("/ubicaciones")
    public String ubicaciones() {
        return "ubicaciones";  // Carga templates/ubicaciones.html
    }

    @GetMapping("/observadores")
    public String observadores() {
        return "observadores";  // Carga templates/observadores.html
    }
}