package com.avistamientos_animales.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Animal")
public class Animal {
    @Id
    @Column(name = "id_animal", length = 10)
    private String idAnimal;

    @Column(name = "nombre_comun", nullable = false)
    private String nombreComun;

    @Column(name = "estado_conservacion", nullable = false)
    private String estadoConservacion;

    @Column(name = "protegido", nullable = false)
    private String protegido;

    @ManyToOne
    @JoinColumn(name = "id_especie", nullable = false)
    private Especie especie;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avistamiento> avistamientos;
    
    // --- Getters y Setters ---

    public String getIdAnimal() {
        return idAnimal;  
    }

    public void setIdAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNombreComun() {
        return nombreComun;  
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getEstadoConservacion() {
        return estadoConservacion;  
    }

    public void setEstadoConservacion(String estadoConservacion) {
        this.estadoConservacion = estadoConservacion;
    }

    public String getProtegido() {
        return protegido;  
    }

    public void setProtegido(String protegido) {
        this.protegido = protegido;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public List<Avistamiento> getAvistamientos() {
        return avistamientos;
    }

    public void setAvistamientos(List<Avistamiento> avistamientos) {
        this.avistamientos = avistamientos;
    }
}
