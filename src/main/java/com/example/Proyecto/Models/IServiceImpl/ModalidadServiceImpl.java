package com.example.Proyecto.Models.IServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.Proyecto.Models.Dao.IModalidadDao;

import com.example.Proyecto.Models.Entity.Modalidad;
import com.example.Proyecto.Models.IService.IModalidadService;

@Service
public class ModalidadServiceImpl implements IModalidadService{

    @Autowired
    private IModalidadDao modalidadDao;

    @Override
    public List<Modalidad> findAll() {
        return (List<Modalidad>) modalidadDao.findAll();
    }

    @Override
    public void save(Modalidad modalidad) {
        modalidadDao.save(modalidad);
    }

    @Override
    public Modalidad findOne(Long id) {
        return modalidadDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        modalidadDao.deleteById(id);
    }
    
}
