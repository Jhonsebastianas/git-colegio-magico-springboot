package com.quipux.colegio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

public class Reto1EntityTest {

    @Test
    public void hechizoDebeSerUnaEntidadCompleta() throws NoSuchFieldException, NoSuchMethodException {
        // 1. Validar @Entity
        assertTrue(HechizoEntity.class.isAnnotationPresent(Entity.class), "¡Error Mágico! Falta la anotación @Entity");
        
        // 2. Validar @Table
        Table table = HechizoEntity.class.getAnnotation(Table.class);
        assertNotNull(table, "Falta la anotación @Table");
        assertEquals("hechizos", table.name(), "El nombre de la tabla debe ser 'hechizos'");
        
        // 3. Validar ID y AutoGenerate
        Field idField = HechizoEntity.class.getDeclaredField("id");
        Method idGetter = HechizoEntity.class.getDeclaredMethod("getId");
        assertTrue(idField.isAnnotationPresent(Id.class) || idGetter.isAnnotationPresent(Id.class),
            "El campo id debe tener la anotación @Id");
        assertTrue(idField.isAnnotationPresent(GeneratedValue.class) || idGetter.isAnnotationPresent(GeneratedValue.class),
            "El campo id debe tener la anotación @GeneratedValue");

        // 4. Validar Column en nombre
        Field nombreField = HechizoEntity.class.getDeclaredField("nombre");
        Column column = nombreField.getAnnotation(Column.class);
        if (column == null) {
            Method nombreGetter = HechizoEntity.class.getDeclaredMethod("getNombre");
            column = nombreGetter.getAnnotation(Column.class);
        }
        
        assertNotNull(column, "El campo nombre debe tener la anotación @Column");
        assertFalse(column.nullable(), "La columna nombre no debe permitir nulos (nullable = false)");
    }
}
