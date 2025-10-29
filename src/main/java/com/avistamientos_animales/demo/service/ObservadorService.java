package com.avistamientos_animales.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.avistamientos_animales.demo.repository.ObservadorRepository;
import com.avistamientos_animales.demo.model.Observador;

import java.util.List;
import java.util.Optional;

@Service
public class ObservadorService {
    @Autowired
    private ObservadorRepository observadorRepository;
    
    public List<Observador> listar() {
        return observadorRepository.findAll();
    }

    public Optional<Observador> buscarPorId(String id) {
        return observadorRepository.findById(id);
    }

    public Observador guardar(Observador o) {
        if (o.getIdObservador() == null || o.getIdObservador().isEmpty()) {
            o.setIdObservador(generarNuevoId());
        }
        return observadorRepository.save(o);
    }
    public void eliminar(String id) {
        observadorRepository.deleteById(id);
    }

     private String generarNuevoId() {
        String ultimoId = observadorRepository.findLastId();
        int nuevoNumero = 1;

        if (ultimoId != null && ultimoId.length() == 10) {
            String numeroStr = ultimoId.substring(5); // obtiene los últimos 5 dígitos
            nuevoNumero = Integer.parseInt(numeroStr) + 1;
        }

        // Formatear con ceros a la izquierda
        return String.format("OBSER%05d", nuevoNumero);
    }
    public Observador obtenerPorId(String id) {
        return observadorRepository.findById(id).orElse(null);
    }

    public void actualizar(Observador observador) {
        observadorRepository.save(observador); // JPA actualiza si el ID ya existe
    }
}