package com.quipux.colegio.manager;

import com.quipux.colegio.dao.HechizoDao;
import com.quipux.colegio.models.HechizoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// RETO 3: Agrega las anotaciones de Spring Boot
@Service
@Transactional(rollbackFor = Exception.class)

public class HechizoManagerImpl implements HechizoManager {

    @Autowired
    private HechizoDao hechizoDao;

    @Override
    public HechizoEntity registrarHechizo(HechizoEntity hechizo) throws Exception {
        
        // RETO 3.1: Validar Reglas Mágicas
        if (hechizo == null || hechizo.getNombre() == null || hechizo.getNombre().trim().isEmpty()) {
            throw new Exception("Nombre invalido");
        }

        if (hechizo.getTipoMagia() != null && "Oscura".equalsIgnoreCase(hechizo.getTipoMagia())) {
            throw new Exception("Magia prohibida en el colegio");
        }
        
        return hechizoDao.guardarHechizo(hechizo);
    }

    @Override
    @Transactional(readOnly = true)
    
    public List<HechizoEntity> buscarMagia(String tipoMagia) {
        return hechizoDao.buscarPorTipo(tipoMagia);
    }
}