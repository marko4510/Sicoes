package com.example.Proyecto.Models.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Proyecto.Models.Entity.Contratacion;


public interface IContratacionDao extends CrudRepository<Contratacion, Long>{
    
    @Query(value = "SELECT * FROM Contratacion AS con WHERE con.gestion_contratacion = ?1",nativeQuery = true)
    public List<Contratacion> findByGestionContratacion(String gestionContratacion);

    @Query(value = "SELECT * FROM Contratacion AS con INNER JOIN con.modalidad mo WHERE mo.nombre_modalidad = ?1", nativeQuery = true)
    public List<Contratacion> findByNombreModalidad(String nombreModalidad);
}
