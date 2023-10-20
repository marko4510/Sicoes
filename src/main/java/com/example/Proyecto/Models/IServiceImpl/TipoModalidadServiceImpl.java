package com.example.Proyecto.Models.IServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyecto.Models.Dao.ITipoModalidadDao;
import com.example.Proyecto.Models.Entity.TipoModalidad;
import com.example.Proyecto.Models.IService.ITipoModalidadService;

@Service
public class TipoModalidadServiceImpl implements ITipoModalidadService{

     @Autowired
    private ITipoModalidadDao tipoModalidadDao;

    @Override
    public List<TipoModalidad> findAll() {
         return (List<TipoModalidad>) tipoModalidadDao.findAll();
    }

    @Override
    public void save(TipoModalidad tipoModalidad) {
       tipoModalidadDao.save(tipoModalidad);
    }

    @Override
    public TipoModalidad findOne(Long id) {
        return tipoModalidadDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        tipoModalidadDao.deleteById(id);
    }
    
}
