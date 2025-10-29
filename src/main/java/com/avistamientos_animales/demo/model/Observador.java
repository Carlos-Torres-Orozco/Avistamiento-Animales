package com.avistamientos_animales.demo.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Observador")
public class Observador {
    @Id
    @Column(name = "id_observador", length = 10)
    private String idObservador;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "institucion")
    private String institucion;

    @Column(name = "pais")
    private String pais;

    // @OneToMany(mappedBy = "observador", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonManagedReference
    // private List<Avistamiento> avistamientos;

    // --- Getters y Setters ---

    public String getIdObservador() {
        return idObservador;
    }

    public void setIdObservador(String idObservador) {
        this.idObservador = idObservador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    // public List<Avistamiento> getAvistamientos() {
    //     return avistamientos;
    // }

    // public void setAvistamientos(List<Avistamiento> avistamientos) {
    //     this.avistamientos = avistamientos;
    // }
}
