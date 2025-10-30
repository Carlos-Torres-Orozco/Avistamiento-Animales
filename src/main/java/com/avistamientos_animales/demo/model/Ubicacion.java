package com.avistamientos_animales.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ubicacion")
public class Ubicacion {
    @Id
    @Column(name = "id_ubicacion", length = 10)
    private String idUbicacion;

    @Column(name = "pais", nullable = false)
    private String pais;
    
    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "habitad", nullable = false)
    private String habitad;

    // @OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonManagedReference
    // private List<Avistamiento> avistamientos;

    // --- Getters y Setters ---
    public String getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(String idUbicacion) {
        this.idUbicacion = idUbicacion;
    }
    
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHabitad() {
        return habitad;
    }

    public void setHabitad(String habitad) {
        this.habitad = habitad;
    }

    // public List<Avistamiento> getAvistamientos() {
    //     return avistamientos;
    // }

    // public void setAvistamientos(List<Avistamiento> avistamientos) {
    //     this.avistamientos = avistamientos;
    // }
}
