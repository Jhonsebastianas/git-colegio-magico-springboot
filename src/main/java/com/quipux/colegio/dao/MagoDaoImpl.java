package com.quipux.colegio.dao;

import com.quipux.colegio.models.MagoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository le avisa a Spring que este archivo es un especialista en conectarse a la Base de Datos.
@Repository
public class MagoDaoImpl implements MagoDao {

    // EntityManager es nuestro "traductor". Él convierte nuestras instrucciones de Java en código de base de datos.
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(MagoEntity mago) {
        // entityManager.persist() guarda el objeto en la base de datos automáticamente.
        // Es la forma elegante y rápida de hacer el equivalente a este código SQL:
        // INSERT INTO magos (nombre, casa) VALUES ('Harry', 'Gryffindor');
        entityManager.persist(mago);
        
        // flush() fuerza a que el guardado se envíe a la base de datos de inmediato en este instante.
        entityManager.flush(); 
    }

    @Override
    public void update(MagoEntity mago) {
        // entityManager.merge() busca si el mago ya existe y lo actualiza con los nuevos datos.
        // Es el equivalente en SQL a: 
        // UPDATE magos SET nombre = 'Nuevo Nombre', casa = 'Nueva Casa' WHERE id = 1;
        entityManager.merge(mago);
        entityManager.flush();
    }

    @Override
    public void delete(Long id) {
        // En SQL puro, para eliminar usamos la palabra clave DELETE FROM.
        // ¡CUIDADO! Siempre debemos poner un WHERE (condición), si olvidamos el WHERE, ¡borraríamos TODOS los magos del colegio!
        String sql = "DELETE FROM magos WHERE id = :id";
        
        // El ":id" se usa por seguridad. Evita que un hacker informático intente inyectar código malicioso (SQL Injection).
        entityManager.createNativeQuery(sql)
                .setParameter("id", id) // Aquí Spring reemplaza de forma segura el ":id" por el número real.
                .executeUpdate(); // Ejecuta la instrucción en la base de datos.
    }

    @Override
    public MagoEntity findById(Long id) {
        // SELECT * significa "Tráeme TODAS las columnas (id, nombre, casa)".
        // Usamos WHERE id = :id porque el ID es único. Es decir, ¡solo puede existir un mago con esa "cédula"!
        String sql = "SELECT * FROM magos WHERE id = :id";
        
        // Al poner MagoEntity.class, Spring hace magia y convierte el resultado de la tabla en un Objeto de Java.
        Query query = entityManager.createNativeQuery(sql, MagoEntity.class);
        query.setParameter("id", id);
        
        try {
            // getSingleResult() es perfecto cuando sabemos que SÓLO puede haber una respuesta.
            // Pero, si le decimos que traiga 1 y no encuentra NINGUNO, la base de datos nos tira un error (NoResultException).
            // Por eso usamos try/catch: Lo intentamos, y si da error, devolvemos 'null' diciendo "no hay nadie con ese ID".
            return (MagoEntity) query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    @Override
    public MagoEntity findByName(String nombre) {
        // Aquí hacemos otra búsqueda (SELECT), pero ahora filtramos por el nombre del mago.
        String sql = "SELECT * FROM magos WHERE nombre = :nombre";
        Query query = entityManager.createNativeQuery(sql, MagoEntity.class);
        query.setParameter("nombre", nombre);
        
        try {
            return (MagoEntity) query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    @Override
    public List<MagoEntity> findAll() {

        // Fíjate que aquí no ponemos la cláusula WHERE. 
        // Al no poner condiciones, la base de datos no filtra nada y nos devuelve TODOS los registros que existan en la tabla "magos".
        // Es como decir: "Dame la lista completa de asistencia del colegio".
        
        String sql = "SELECT * FROM magos";
        return entityManager.createNativeQuery(sql, MagoEntity.class).getResultList();
    }
}
