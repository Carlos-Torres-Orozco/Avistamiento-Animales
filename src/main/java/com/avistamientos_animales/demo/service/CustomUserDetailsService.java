package com.avistamientos_animales.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.avistamientos_animales.demo.model.Observador;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ObservadorService observadorService;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Observador obs = observadorService.buscarPorCorreo(correo);

        if (obs == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + correo);
        }

        return User.builder()
                .username(obs.getCorreo())
                .password(obs.getContrasena())   // DEBE estar encriptada
                .roles(obs.getRol().toUpperCase())             // "observador", "admin", etc.
                .build();
    }
}