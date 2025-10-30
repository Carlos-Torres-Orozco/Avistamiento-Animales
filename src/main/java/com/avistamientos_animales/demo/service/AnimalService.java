package com.avistamientos_animales.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avistamientos_animales.demo.model.Animal;
import com.avistamientos_animales.demo.repository.AnimalRepository;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> listar(){
        return animalRepository.findAll();
    }

    public Optional<Animal> buscarPorId(String id) {
        return animalRepository.findById(id);
    }
    public Animal guardar(Animal a){
        if(a.getIdAnimal()==null || a.getIdAnimal().isEmpty()){
            a.setIdAnimal(generarNuevoId());
        }
        return animalRepository.save(a);
    }
    public void eliminar(String id) {
        animalRepository.deleteById(id);
    }
    private String generarNuevoId() {
        String ultimoId = animalRepository.findLastId();
        int nuevoNumero = 1;

        if (ultimoId != null && ultimoId.length() == 10) {
            String numeroStr = ultimoId.substring(5); // obtiene los últimos 5 dígitos
            nuevoNumero = Integer.parseInt(numeroStr) + 1;
        }

        // Formatear con ceros a la izquierda
        return String.format("ANIMA%05d", nuevoNumero);
    }
    public Animal obtenerPorId(String id) {
        return animalRepository.findById(id).orElse(null);
    }
    public void actualizar(Animal animal) {
        animalRepository.save(animal); // JPA actualiza si el ID ya existe
    }
}
