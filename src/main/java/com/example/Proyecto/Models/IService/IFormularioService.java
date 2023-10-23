package com.example.Proyecto.Models.IService;

import java.util.List;

import com.example.Proyecto.Models.Entity.Formulario;



public interface IFormularioService {

    public List<Formulario> findAll();

    public void save(Formulario formulario);

    public Formulario findOne(Long id);

    public void delete(Long id);
}
