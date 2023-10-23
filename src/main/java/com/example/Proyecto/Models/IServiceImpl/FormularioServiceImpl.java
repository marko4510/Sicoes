package com.example.Proyecto.Models.IServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyecto.Models.Dao.IFormularioDao;
import com.example.Proyecto.Models.Entity.Formulario;

import com.example.Proyecto.Models.IService.IFormularioService;



@Service
public class FormularioServiceImpl implements IFormularioService{

    @Autowired
    private IFormularioDao formularioDao;


    @Override
    public List<Formulario> findAll() {
         return (List<Formulario>) formularioDao.findAll();
    }

    @Override
    public void save(Formulario formulario) {
        formularioDao.save(formulario);
    }

    @Override
    public Formulario findOne(Long id) {
        return formularioDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        formularioDao.deleteById(id);
    }
    
}
