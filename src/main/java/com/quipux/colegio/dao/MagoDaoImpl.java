package com.quipux.colegio.dao;

import org.springframework.stereotype.Repository;

import com.quipux.colegio.models.MagoEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository 
public class MagoDaoImpl {

    @PersistenceContext 
    private EntityManager entityManager;

    public void guardar(MagoEntity mago){
        entityManager.persist(mago);
    }

    public MagoEntity buscarPorNombre(String nombre){
        Query query = entityManager.createQuery("SELECT m FROM MagoEntity m WHERE m.nombre = :nombre");
        return (MagoEntity) query.getResultList().get(0);
        
    }

}
