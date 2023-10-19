package com.example.Proyecto.Models.IServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.Proyecto.Models.Dao.IUnidadDao;

import com.example.Proyecto.Models.Entity.Unidad;
import com.example.Proyecto.Models.IService.IUnidadService;

@Service
public class UnidadServiceImpl implements IUnidadService{

    @Autowired
    private IUnidadDao unidadDao;

    @Override
    public List<Unidad> findAll() {
         return (List<Unidad>) unidadDao.findAll();
    }

    @Override
    public void save(Unidad unidad) {
        unidadDao.save(unidad);
    }

    @Override
    public Unidad findOne(Long id) {
        return unidadDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        unidadDao.deleteById(id);
    }
    
}
