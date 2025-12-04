package com.avistamientos_animales.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avistamientos_animales.demo.model.Multimedia;
import com.avistamientos_animales.demo.repository.MultimediaRepository;

@Service
public class MultimediaService {

    @Autowired
    private MultimediaRepository multimediaRepository;

    public List<Multimedia> listar() {
        return multimediaRepository.findAll();
    }

    public Optional<Multimedia> buscarPorId(String id) {
        return multimediaRepository.findById(id);
    }

    @Transactional
    public Multimedia guardar(Multimedia m) {
        if (m.getIdImagen() == null || m.getIdImagen().isEmpty()) {
            m.setIdImagen(generarNuevoId());
        }
        return multimediaRepository.save(m);
    }

    public void eliminar(String id) {
        multimediaRepository.deleteById(id);
    }

    public Multimedia obtenerPorId(String id) {
        return multimediaRepository.findById(id).orElse(null);
    }

    @Transactional
    public void actualizar(Multimedia multimedia) {
        if (multimedia.getIdImagen() != null && !multimedia.getIdImagen().isEmpty()) {
            multimediaRepository.save(multimedia); // Hibernate actualizará
        } else {
            throw new IllegalArgumentException("El ID de Multimedia no puede ser nulo al actualizar.");
        }
    }

    // Genera un ID único tipo MULTI00001
    public String generarNuevoId() {
        String ultimoId = multimediaRepository.findLastId();
        int nuevoNumero = 1;

        if (ultimoId != null && ultimoId.length() == 10) {
            String numeroStr = ultimoId.substring(5); // Extrae los últimos 5 dígitos
            nuevoNumero = Integer.parseInt(numeroStr) + 1;
        }

        return String.format("MULTI%05d", nuevoNumero);
    }
}
