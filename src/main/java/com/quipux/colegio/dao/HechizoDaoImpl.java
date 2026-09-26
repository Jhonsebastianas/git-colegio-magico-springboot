package com.quipux.colegio.dao;

import com.quipux.colegio.models.HechizoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

// RETO 2: Asegúrate de que esta clase sea reconocida como un repositorio en Spring (anotación de clase)

@Repository
public class HechizoDaoImpl implements HechizoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public HechizoEntity guardarHechizo(HechizoEntity hechizo) {
        // RETO 2.3: Usa el EntityManager para persistir (guardar) el hechizo en la Base de Datos.
        // Escribe tu código aquí:
        entityManager.persist(hechizo);
        
        return hechizo;
    }

    @Override
    public List<HechizoEntity> buscarPorTipo(String tipoMagia) {
        // RETO 2.1: Crea el Query de JPA (JPQL) de manera segura para evitar inyección de dependencias (SQL Injection).
        // Recuerda usar ":tipo" y luego el setParameter().
        // Reemplaza null por tu código.
        
        TypedQuery<HechizoEntity> query = entityManager.createQuery(
            "SELECT h FROM HechizoEntity h WHERE h.tipoMagia = :tipo", HechizoEntity.class);
        query.setParameter("tipo", tipoMagia);
        
        return query.getResultList();
    }

    @Override
    public HechizoEntity buscarPorNombre(String nombre) {
        // RETO 2.2: Completa la búsqueda segura por nombre
        TypedQuery<HechizoEntity> query = entityManager.createQuery(
            "SELECT h FROM HechizoEntity h WHERE h.nombre = :nombre", HechizoEntity.class);
        query.setParameter("nombre", nombre);
        
        List<HechizoEntity> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }
}
