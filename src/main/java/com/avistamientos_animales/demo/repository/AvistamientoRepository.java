package com.avistamientos_animales.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avistamientos_animales.demo.model.Avistamiento;

public interface  AvistamientoRepository extends JpaRepository<Avistamiento, String>{
    @Query(value = "SELECT TOP 1 id_avistamiento FROM Avistamiento ORDER BY id_avistamiento DESC", nativeQuery = true)
    String findLastId();
}
