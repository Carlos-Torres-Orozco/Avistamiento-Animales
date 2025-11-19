package com.avistamientos_animales.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.avistamientos_animales.demo.model.Observador;
import com.avistamientos_animales.demo.service.ObservadorService;

@Controller
public class LoginController {
    @Autowired
    private ObservadorService observadorService;
    
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("loginData", new Observador());
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo,
                                @RequestParam String contrasena,
                                Model model) {

        Observador usuario = observadorService.buscarPorCorreo(correo);

        if (usuario == null || !usuario.getContrasena().equals(contrasena)) {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            model.addAttribute("loginData", new Observador());
            return "login";
        }

        if ("admin".equalsIgnoreCase(usuario.getRol())) {
            return "redirect:/admin/home";
        }

        return "redirect:/observador/home";
    }
}
