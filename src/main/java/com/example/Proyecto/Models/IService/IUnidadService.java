package com.example.Proyecto.Models.IService;

import java.util.List;


import com.example.Proyecto.Models.Entity.Unidad;

public interface IUnidadService {

    public List<Unidad> findAll();

    public void save(Unidad unidad);

    public Unidad findOne(Long id);

    public void delete(Long id);
}
