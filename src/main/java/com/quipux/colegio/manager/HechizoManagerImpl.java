package com.quipux.colegio.manager;

import com.quipux.colegio.dao.HechizoDao;
import com.quipux.colegio.models.HechizoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// RETO 3: Agrega las anotaciones de Spring Boot para que esta clase sea un Servicio de Lógica
// y para que los métodos se ejecuten dentro de una transacción de base de datos.
// PISTAS: @S... y @T...
@Service 
@Transactional 
public class HechizoManagerImpl implements HechizoManager {

    @Autowired
    private HechizoDao hechizoDao;

    @Override
    public HechizoEntity registrarHechizo(HechizoEntity hechizo) throws Exception {
        // RETO 3.1: Validar Reglas Mágicas
        // 1. Si el nombre del hechizo es nulo o está vacío, debes lanzar una Exception con el mensaje "Nombre invalido".
        // 2. Si el "tipoMagia" del hechizo es "Oscura", debes lanzar una Exception con el mensaje "Magia prohibida en el colegio".
        
        // Escribe tu código aquí:
        if ("Oscura".equals(hechizo.getTipoMagia()) ) {
            throw new Exception("Magia prohibida en el colegio");
        }
        if (hechizo.getNombre() == null || hechizo.getNombre().trim().isEmpty()) {
            throw new Exception("Nombre invalido");
        }
        
        return hechizoDao.guardarHechizo(hechizo);
    }

    @Override
    public List<HechizoEntity> buscarMagia(String tipoMagia) {
        return hechizoDao.buscarPorTipo(tipoMagia);
    }
}
