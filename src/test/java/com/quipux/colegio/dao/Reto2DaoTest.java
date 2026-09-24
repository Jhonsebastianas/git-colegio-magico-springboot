  package com.quipux.colegio.dao;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class Reto2DaoTest {

    @Test
    public void daoDebeSerRepository() {
        boolean isRepository = HechizoDaoImpl.class.isAnnotationPresent(Repository.class);
        assertTrue(isRepository, "HechizoDaoImpl debe tener la anotación @Repository");
    }

    @Test
    public void daoDebeUsarSetParameterYPersist() throws Exception {
        // Leemos el código fuente para validar que el estudiante usó persist y setParameter
        // Esto es magia de reflexión combinada con análisis de código
        String code = new String(Files.readAllBytes(Paths.get("src/main/java/com/quipux/colegio/dao/HechizoDaoImpl.java")));
        
        assertTrue(code.contains("entityManager.persist("), "Debes usar persist() para guardar el hechizo");
        assertTrue(code.contains("setParameter"), "Debes usar setParameter() en tus consultas para evitar Inyección SQL");
        assertTrue(code.contains(":tipo") || code.contains(":nombre"), "Debes usar parámetros con dos puntos, ej: :tipo");
        assertFalse(Pattern.compile("\\+\\s*tipoMagia").matcher(code).find(), "¡PELIGRO! Estás concatenando variables en la consulta (Inyección SQL). Usa parámetros.");
    }
}
