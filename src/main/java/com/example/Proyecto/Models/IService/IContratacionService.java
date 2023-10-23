package com.example.Proyecto.Models.IService;

import java.util.List;

import com.example.Proyecto.Models.Entity.Contratacion;


public interface IContratacionService {

      public List<Contratacion> findAll();

    public void save(Contratacion contratacion);

    public Contratacion findOne(Long id);

    public void delete(Long id);
}
