package com.example.Proyecto.Models.IService;

import java.util.List;


import com.example.Proyecto.Models.Entity.Modalidad;

public interface IModalidadService {

    public List<Modalidad> findAll();

    public void save(Modalidad modalidad);

    public Modalidad findOne(Long id);

    public void delete(Long id);
}
