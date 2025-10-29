package com.avistamientos_animales.demo.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Especie")
public class Especie {
    @Id
    @Column(name = "id_especie", length = 10)
    private String idEspecie;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "familia", nullable = false)
    private String familia;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "orden", nullable = false)
    private String orden;

    @Column(name = "clase", nullable = false)
    private String clase;

    @Column(name = "filo", nullable = false)
    private String filo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "especie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Animal> animales;

    // --- Getters y Setters ---
    public String getIdEspecie() {
        return idEspecie;
    }
    public void setIdEspecie(String idEspecie) {
        this.idEspecie = idEspecie;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFamilia() {
        return familia;
    }
    public void setFamilia(String familia) {
        this.familia = familia;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getOrden() {
        return orden;
    }
    public void setOrden(String orden) {
        this.orden = orden;
    }
    public String getClase() {
        return clase;
    }
    public void setClase(String clase) {
        this.clase = clase;
    }
    public String getFilo() {
        return filo;
    }
    public void setFilo(String filo) {
        this.filo = filo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
