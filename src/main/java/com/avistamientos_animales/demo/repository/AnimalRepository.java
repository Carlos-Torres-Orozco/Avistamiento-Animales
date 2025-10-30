package com.avistamientos_animales.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avistamientos_animales.demo.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, String> {
    @Query(value = "SELECT TOP 1 id_animal FROM Animal ORDER BY id_especie DESC", nativeQuery = true)
    String findLastId();
}
