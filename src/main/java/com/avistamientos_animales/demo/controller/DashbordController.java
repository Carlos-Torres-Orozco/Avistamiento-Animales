package com.avistamientos_animales.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import com.avistamientos_animales.demo.model.Observador;

@Controller
public class DashbordController {

    @GetMapping("/admin/dashboard")
    public String dashboardAdmin(HttpSession session, Model model) {
        Observador usuario = (Observador) session.getAttribute("usuario");
        if (usuario == null || !"admin".equalsIgnoreCase(usuario.getRol())) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        return "dashboard-admin";
    }

    @GetMapping("/observador/dashboard")
    public String dashboardObservador(HttpSession session, Model model) {
        Observador usuario = (Observador) session.getAttribute("usuario");
        if (usuario == null || !"observador".equalsIgnoreCase(usuario.getRol())) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        return "dashboard-observador";
    }
}
