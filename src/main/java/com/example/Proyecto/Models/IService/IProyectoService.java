package com.example.Proyecto.Models.IService;

import java.util.List;

import com.example.Proyecto.Models.Entity.Proyecto;

public interface IProyectoService {
    public List<Proyecto> findAll();

    public void save(Proyecto proyecto);

    public Proyecto findOne(Long id);

    public void delete(Long id);

}
