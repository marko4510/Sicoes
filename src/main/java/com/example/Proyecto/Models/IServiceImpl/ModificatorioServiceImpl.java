package com.example.Proyecto.Models.IServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyecto.Models.Dao.IModificatorioDao;
import com.example.Proyecto.Models.Entity.Modificatorio;
import com.example.Proyecto.Models.IService.IModificatorioService;

@Service
public class ModificatorioServiceImpl implements IModificatorioService{

    @Autowired
    private IModificatorioDao modificatorioDao;

    @Override
    public List<Modificatorio> findAll() {
        return (List<Modificatorio>) modificatorioDao.findAll();
    }

    @Override
    public void save(Modificatorio modificatorio) {
        modificatorioDao.save(modificatorio);
    }

    @Override
    public Modificatorio findOne(Long id) {
        return modificatorioDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        modificatorioDao.deleteById(id);
    }

    @Override
    public Long cantidadModificatorio(Long id_modificatorio) {
        return modificatorioDao.cantidadModificatorio(id_modificatorio);
    }

    
    
}
