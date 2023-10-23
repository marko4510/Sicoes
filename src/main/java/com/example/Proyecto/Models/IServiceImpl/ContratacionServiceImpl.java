package com.example.Proyecto.Models.IServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyecto.Models.Dao.IContratacionDao;
import com.example.Proyecto.Models.Entity.Contratacion;
import com.example.Proyecto.Models.IService.IContratacionService;


@Service
public class ContratacionServiceImpl implements IContratacionService{

    @Autowired
    private IContratacionDao contratacionDao;

    @Override
    public List<Contratacion> findAll() {
        return (List<Contratacion>) contratacionDao.findAll();
    }

    @Override
    public void save(Contratacion contratacion) {
        contratacionDao.save(contratacion);
    }

    @Override
    public Contratacion findOne(Long id) {
        return contratacionDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        contratacionDao.deleteById(id);
    }
    
}
