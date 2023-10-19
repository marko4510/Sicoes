package com.example.Proyecto.Models.IService;

import java.util.List;

import com.example.Proyecto.Models.Entity.Grado;

public interface IGradoService {


    public List<Grado> findAll();

    public void save(Grado grado);

    public Grado findOne(Long id);

    public void delete(Long id);
}
