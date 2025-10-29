package com.avistamientos_animales.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.avistamientos_animales.demo.repository.EspecieRepository;
import com.avistamientos_animales.demo.model.Especie;
import java.util.List;
import java.util.Optional;

@Service
public class EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    public List<Especie> listar() {
        return especieRepository.findAll();
    }
    public Optional<Especie> buscarPorId(String id) {
        return especieRepository.findById(id);
    }
    public Especie guardar(Especie e) {
        if (e.getIdEspecie() == null || e.getIdEspecie().isEmpty()) {
            e.setIdEspecie(generarNuevoId());
        }
        return especieRepository.save(e);
    }
    public void eliminar(String id) {
        especieRepository.deleteById(id);
    }
    private String generarNuevoId() {
        String ultimoId = especieRepository.findLastId();
        int nuevoNumero = 1;

        if (ultimoId != null && ultimoId.length() == 10) {
            String numeroStr = ultimoId.substring(5); // obtiene los últimos 5 dígitos
            nuevoNumero = Integer.parseInt(numeroStr) + 1;
        }

        // Formatear con ceros a la izquierda
        return String.format("ESPEC%05d", nuevoNumero);
    }
    public Especie obtenerPorId(String id) {
        return especieRepository.findById(id).orElse(null);
    }
    public void actualizar(Especie especie) {
        especieRepository.save(especie); // JPA actualiza si el ID ya existe
    }
}
