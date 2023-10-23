package com.example.Proyecto.Models.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.Proyecto.Models.Entity.Proyecto;

public interface IProyectoDao extends CrudRepository<Proyecto, Long>{
    
}
