package com.quipux.colegio.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// RETO 1: Agrega las anotaciones necesarias para que esta clase sea una Entidad de Base de Datos.
// PISTAS: 
// 1. Necesitas marcar la clase con @E...
// 2. Necesitas mapearla a una tabla llamada "hechizos" con @T...
// 3. El atributo 'id' debe ser la llave primaria (@I...) y autogenerada (@G...)
// 4. El atributo 'nombre' debe mapearse a una columna (@C...) y no debe permitir nulos (nullable = false).
@Entity
@Table(name = "hechizos")
public class HechizoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre" , nullable = false)
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
