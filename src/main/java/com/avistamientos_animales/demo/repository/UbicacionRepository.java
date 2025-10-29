package com.avistamientos_animales.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avistamientos_animales.demo.model.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion, String> {
    @Query(value = "SELECT TOP 1 id_ubicacion FROM Ubicacion ORDER BY id_ubicacion DESC", nativeQuery = true)
    String findLastId();
    
}
