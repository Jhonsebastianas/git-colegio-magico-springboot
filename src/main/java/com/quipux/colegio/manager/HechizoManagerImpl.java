package com.quipux.colegio.manager;

import com.quipux.colegio.dao.HechizoDao;
import com.quipux.colegio.models.HechizoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HechizoManagerImpl implements HechizoManager {

    @Autowired
    private HechizoDao hechizoDao;

    @Override
    public HechizoEntity registrarHechizo(HechizoEntity hechizo) throws Exception {

        if ("Oscura".equals(hechizo.getTipoMagia())) {
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