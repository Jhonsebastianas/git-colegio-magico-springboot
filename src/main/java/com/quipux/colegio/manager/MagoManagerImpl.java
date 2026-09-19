package com.quipux.colegio.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quipux.colegio.dao.MagoDaoImpl;
import com.quipux.colegio.models.MagoEntity;

@Service 
public class MagoManagerImpl {

    @Autowired 
    private MagoDaoImpl magoDao;

    @Transactional 
    public void registrarMago(MagoEntity mago) throws Exception{
        if(mago.getNombre() == null || mago.getNombre().isEmpty()) {
            throw new Exception("El mago debe de tener su nombre");
        }
        magoDao.guardar(mago);
    }

}
