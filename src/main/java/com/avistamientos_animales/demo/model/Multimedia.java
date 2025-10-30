package com.avistamientos_animales.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Multimedia")
public class Multimedia {
    @Id
    @Column(name = "id_imagen", length = 10)
    private String idImagen;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "archivo", nullable = false)
    private String archivo;

    @Column(name = "fecha", nullable = false)
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "id_avistamiento", nullable = false)
    private Avistamiento avistamiento;

    // --- Getters y Setters ---

    public String getIdImagen() {
        return idImagen;  
    }

    public void setIdImagen(String idImagen) {
        this.idImagen = idImagen;
    }

    public String getTipo() {
        return tipo;  
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArchivo() {
        return archivo;  
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getFecha() {
        return fecha;  
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Avistamiento getAvistamiento() {
        return avistamiento;
    }

    public void setAvistamiento(Avistamiento avistamiento) {
        this.avistamiento = avistamiento;
    } 
}
