package com.avistamientos_animales.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avistamientos_animales.demo.model.Especie;

public interface EspecieRepository extends JpaRepository<Especie, String> {
    @Query(value = "SELECT TOP 1 id_especie FROM Especie ORDER BY id_especie DESC", nativeQuery = true)
    String findLastId();
    
}
