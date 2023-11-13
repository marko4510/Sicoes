package com.example.Proyecto.Models.IService;

import java.util.List;

import com.example.Proyecto.Models.Entity.Contratacion;


public interface IContratacionService {

  public List<Contratacion> findAll();

  public void save(Contratacion contratacion);

  public Contratacion findOne(Long id);

  public void delete(Long id);

  public List<Contratacion> findByGestionContratacion(String gestionContratacion);

  public List<Contratacion> findByNombreModalidad(String nombreModalidad);

  public List<Contratacion> findByGestion_y_Unidad(String gestionContratacion, String nombreUnidad);

  public List<Contratacion> findByGestion_y_NumProyecto(String gestionContratacion, String numProyecto);

  public List<Contratacion> findByGestion_y_TipoModalidad(String gestionContratacion, String nomTipoModalidad);

  public List<Contratacion> findByGestion_y_Persona(String gestionContratacion, Long id_Persona);

  public List<Contratacion> findByGestion_y_Modalidad(String gestionContratacion, String nombreModalidad);

  public List<Contratacion> findByGestion_TipoModalidad_Unidad(String gestionContratacion, String nomTipoModalidad, String nombreUnidad);

  public List<Contratacion> findByGestion_Modalidad_TipoModalidad(String gestionContratacion, String nombreModalidad, String nomTipoModalidad);

  public List<Contratacion> findByGestion_Modalidad_NumProyecto(String gestionContratacion, String nombreModalidad, String numProyecto);


}
