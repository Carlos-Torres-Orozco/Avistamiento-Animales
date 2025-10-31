package com.avistamientos_animales.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avistamientos_animales.demo.model.Multimedia;
import com.avistamientos_animales.demo.repository.MultimediaRepository;

@Service
public class MultimediaService {
    @Autowired
    private MultimediaRepository multimediaRepository;
    
    public List<Multimedia> listar(){
        return multimediaRepository.findAll();
    }

    public Optional<Multimedia> buscarPorId(String id) {
        return multimediaRepository.findById(id);
    }

    public Multimedia guardar(Multimedia m){
        if(m.getIdImagen()==null || m.getIdImagen().isEmpty()){
            m.setIdImagen(generarNuevoId());
        }
        return multimediaRepository.save(m);
    }
    public void eliminar(String id) {
        multimediaRepository.deleteById(id);
    }
    private String generarNuevoId() {
        String ultimoId = multimediaRepository.findLastId();
        int nuevoNumero = 1;

        if (ultimoId != null && ultimoId.length() == 10) {
            String numeroStr = ultimoId.substring(5); // obtiene los últimos 5 dígitos
            nuevoNumero = Integer.parseInt(numeroStr) + 1;
        }

        // Formatear con ceros a la izquierda
        return String.format("MULTI%05d", nuevoNumero);
    }
    public Multimedia obtenerPorId(String id) {
        return multimediaRepository.findById(id).orElse(null);
    }
    public void actualizar(Multimedia multimedia) {
        multimediaRepository.save(multimedia); // JPA actualiza si el ID ya existe
    }
}
