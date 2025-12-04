package com.avistamientos_animales.demo.model;

import java.time.LocalDate;
import jakarta.persistence.*;

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
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_avistamiento", nullable = false)
    private Avistamiento avistamiento;

    // --- Getters y Setters ---
    public String getIdImagen() { return idImagen; }
    public void setIdImagen(String idImagen) { this.idImagen = idImagen; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getArchivo() { return archivo; }
    public void setArchivo(String archivo) { this.archivo = archivo; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Avistamiento getAvistamiento() { return avistamiento; }
    public void setAvistamiento(Avistamiento avistamiento) { this.avistamiento = avistamiento; }

    // Asignar fecha automáticamente si no está definida
    @PrePersist
    public void prePersist() {
        if (this.fecha == null) {
            this.fecha = LocalDate.now();
        }
    }
}
