package com.avistamientos_animales.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avistamientos_animales.demo.model.Observador;
import com.avistamientos_animales.demo.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String correo,
            @RequestParam String contrasena,
            HttpSession session,
            Model model) {

        Observador usuario = loginService.autenticar(correo, contrasena);

        if (usuario != null) {
            session.setAttribute("usuario", usuario);
            if ("admin".equalsIgnoreCase(usuario.getRol())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/observador/dashboard";
            }
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
