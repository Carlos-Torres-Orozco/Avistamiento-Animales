package com.avistamientos_animales.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.avistamientos_animales.demo.model.Observador;
import com.avistamientos_animales.demo.repository.ObservadorRepository;

@Service
public class LoginService {

    @Autowired
    private ObservadorRepository observadorRepository;

    public Observador autenticar(String correo, String contrasena) {
        return observadorRepository.findByCorreoAndContrasena(correo, contrasena);
    }
}