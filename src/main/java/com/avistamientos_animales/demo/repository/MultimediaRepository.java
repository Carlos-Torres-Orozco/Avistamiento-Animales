package com.avistamientos_animales.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avistamientos_animales.demo.model.Multimedia;

public interface MultimediaRepository extends JpaRepository<Multimedia, String>{
    @Query(value = "SELECT TOP 1 id_multimedia FROM Multimedia ORDER BY id_multimedia DESC", nativeQuery = true)
    String findLastId();
}
