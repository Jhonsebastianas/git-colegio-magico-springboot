package com.quipux.colegio.manager;

import com.quipux.colegio.dao.HechizoDao;
import com.quipux.colegio.models.HechizoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// RETO 3: Agrega las anotaciones de Spring Boot para que esta clase sea un Servicio de Lógica
// y para que los métodos se ejecuten dentro de una transacción de base de datos.
@Service       // (1) Marca la clase como componente de capa de servicio en Spring
@Transactional // (2) Maneja las transacciones de base de datos automáticamente
public class HechizoManagerImpl implements HechizoManager {

    @Autowired
    private HechizoDao hechizoDao;

    @Override
    public HechizoEntity registrarHechizo(HechizoEntity hechizo) throws Exception {
        // Validación 1: tipo de magia prohibido
        if ("Oscura".equals(hechizo.getTipoMagia())) {
            throw new Exception("Magia prohibida en el colegio");   // (3)
        }

        // Validación 2: nombre vacío o nulo
        if (hechizo.getNombre() == null || hechizo.getNombre().trim().isEmpty()) {
            throw new Exception("Nombre invalido");   // (4)
        }

        return hechizoDao.guardarHechizo(hechizo);
    }

    @Override
    public List<HechizoEntity> buscarMagia(String tipoMagia) {
        return hechizoDao.buscarPorTipo(tipoMagia);
    }
}