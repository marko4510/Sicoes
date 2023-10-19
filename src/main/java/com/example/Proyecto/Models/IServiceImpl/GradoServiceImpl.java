package com.example.Proyecto.Models.IServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyecto.Models.Dao.IGradoDao;
import com.example.Proyecto.Models.Entity.Grado;
import com.example.Proyecto.Models.IService.IGradoService;


@Service
public class GradoServiceImpl implements IGradoService{

    @Autowired
    private IGradoDao gradoDao;

    @Override
    public List<Grado> findAll() {
         return (List<Grado>) gradoDao.findAll();
    }

    @Override
    public void save(Grado grado) {
        gradoDao.save(grado);
    }

    @Override
    public Grado findOne(Long id) {
        return gradoDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        gradoDao.deleteById(id);
    }
    
}
