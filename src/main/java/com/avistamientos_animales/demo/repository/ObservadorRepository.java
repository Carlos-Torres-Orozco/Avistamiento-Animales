package com.avistamientos_animales.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avistamientos_animales.demo.model.Observador;

public interface ObservadorRepository extends JpaRepository<Observador, String> {
    @Query(value = "SELECT TOP 1 id_observador FROM Observador ORDER BY id_observador DESC", nativeQuery = true)
    String findLastId();
}
