package com.example.Proyecto.Models.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.Proyecto.Models.Entity.Formulario;



public interface IFormularioDao extends CrudRepository<Formulario, Long> {
    
}
