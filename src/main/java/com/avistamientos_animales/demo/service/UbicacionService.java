package com.avistamientos_animales.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.avistamientos_animales.demo.repository.UbicacionRepository;
import com.avistamientos_animales.demo.model.Ubicacion;

import java.util.List;
import java.util.Optional;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public List<Ubicacion> listar() {
        return ubicacionRepository.findAll();
    }

    public Optional<Ubicacion> buscarPorId(String id) {
        return ubicacionRepository.findById(id);
    }

    public Ubicacion guardar(Ubicacion u) {
        if (u.getIdUbicacion() == null || u.getIdUbicacion().isEmpty()) {
            u.setIdUbicacion(generarNuevoId());
        }
        return ubicacionRepository.save(u);
    }

    public void eliminar(String id) {
        ubicacionRepository.deleteById(id);
    }

    private String generarNuevoId() {
        String ultimoId = ubicacionRepository.findLastId();
        int nuevoNumero = 1;

        if (ultimoId != null && ultimoId.length() == 10) {
            String numeroStr = ultimoId.substring(5); // obtiene los últimos 5 dígitos
            nuevoNumero = Integer.parseInt(numeroStr) + 1;
        }

        // Formatear con ceros a la izquierda
        return String.format("UBICA%05d", nuevoNumero);
    }

    public Ubicacion obtenerPorId(String id) {
        return ubicacionRepository.findById(id).orElse(null);
    }

    public void actualizar(Ubicacion ubicacion) {
        ubicacionRepository.save(ubicacion); // JPA actualiza si el ID ya existe
    }
}
