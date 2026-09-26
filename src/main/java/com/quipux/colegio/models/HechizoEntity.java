package com.quipux.colegio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

// RETO 1: Agrega las anotaciones necesarias para que esta clase sea una Entidad de Base de Datos.
@Entity
@Table(name = "hechizos")
public class HechizoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    private String tipoMagia; // Ejemplo: Fuego, Agua, Oscura
    private Integer nivelPoder;
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipoMagia() { return tipoMagia; }
    public void setTipoMagia(String tipoMagia) { this.tipoMagia = tipoMagia; }
    public Integer getNivelPoder() { return nivelPoder; }
    public void setNivelPoder(Integer nivelPoder) { this.nivelPoder = nivelPoder; }
}