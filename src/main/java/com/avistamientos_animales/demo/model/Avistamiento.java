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
@Table(name = "Avistamiento")
public class Avistamiento {
    @Id
    @Column(name = "id_avistamiento", length = 10)
    private String idAvistamiento;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "clima", nullable = false)
    private String clima;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "cantidad", nullable = false)
    private String cantidad;

    @ManyToOne
    @JoinColumn(name = "id_animal", nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "id_observador", nullable = false)
    private Observador observador;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion", nullable = false)
    private Ubicacion ubicacion;

    @OneToMany(mappedBy = "avistamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Multimedia> multimedias;

    // --- Getters y Setters ---

    public String getIdAvistamiento() {
        return idAvistamiento;  
    }

    public void setIdAvistamiento(String idAnimal) {
        this.idAvistamiento = idAnimal;
    }

    public String getTipo() {
        return tipo;  
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClima() {
        return clima;  
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getDescripcion() {
        return descripcion;  
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;  
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    public Observador getObservador() {
        return observador;
    }

    public void setObservador(Observador observador) {
        this.observador = observador;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setOUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    } 

    public List<Multimedia> getMultimedias() {
        return multimedias;
    }

    public void setMultimedias(List<Multimedia> multimedias) {
        this.multimedias = multimedias;
    }
}
