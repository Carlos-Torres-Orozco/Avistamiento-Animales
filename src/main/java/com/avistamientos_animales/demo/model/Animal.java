package com.avistamientos_animales.demo.model;

import jakarta.persistence.*;

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

    // @ManyToOne
    // @JoinColumn(name = "id_especie", nullable = false)
}
