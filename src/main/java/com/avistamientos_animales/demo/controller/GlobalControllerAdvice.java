package com.avistamientos_animales.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.avistamientos_animales.demo.model.Observador;
import com.avistamientos_animales.demo.service.ObservadorService;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private ObservadorService observadorService;

    @ModelAttribute("usuario")
    public Object agregarUsuarioAlModelo(Principal principal) {
        if (principal != null) {
            // Si es observador
            Observador obs = observadorService.buscarPorCorreo(principal.getName());
            if (obs != null) {
                return obs;
            }
            // Si no, asumimos que es admin
            return new Object() {
                public String getNombre() { return principal.getName(); }
                public String getRol() { return "ADMIN"; }
            };
        }
        return null;
    }
}
