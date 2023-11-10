package com.example.Proyecto.Models.IService;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.Proyecto.Models.Entity.Contratacion;


public interface IContratacionService {

  public List<Contratacion> findAll();

  public void save(Contratacion contratacion);

  public Contratacion findOne(Long id);

  public void delete(Long id);

  public List<Contratacion> findByGestionContratacion(String gestionContratacion);

  public List<Contratacion> findByNombreModalidad(String nombreModalidad);

}
