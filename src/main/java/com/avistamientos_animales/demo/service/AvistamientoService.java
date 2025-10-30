package com.avistamientos_animales.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avistamientos_animales.demo.model.Avistamiento;
import com.avistamientos_animales.demo.repository.AvistamientoRepository;

@Service
public class AvistamientoService {
     @Autowired
    private AvistamientoRepository avistamientoRepository;

    public List<Avistamiento> listar(){
        return avistamientoRepository.findAll();
    }

    public Optional<Avistamiento> buscarPorId(String id) {
        return avistamientoRepository.findById(id);
    }
    public Avistamiento guardar(Avistamiento a){
        if(a.getIdAvistamiento()==null || a.getIdAvistamiento().isEmpty()){
            a.setIdAvistamiento(generarNuevoId());
        }
        return avistamientoRepository.save(a);
    }
    public void eliminar(String id) {
        avistamientoRepository.deleteById(id);
    }
    private String generarNuevoId() {
        String ultimoId = avistamientoRepository.findLastId();
        int nuevoNumero = 1;

        if (ultimoId != null && ultimoId.length() == 10) {
            String numeroStr = ultimoId.substring(5); // obtiene los últimos 5 dígitos
            nuevoNumero = Integer.parseInt(numeroStr) + 1;
        }

        // Formatear con ceros a la izquierda
        return String.format("AVIST%05d", nuevoNumero);
    }
    public Avistamiento obtenerPorId(String id) {
        return avistamientoRepository.findById(id).orElse(null);
    }
    public void actualizar(Avistamiento avistamiento) {
        avistamientoRepository.save(avistamiento); // JPA actualiza si el ID ya existe
    }
}
