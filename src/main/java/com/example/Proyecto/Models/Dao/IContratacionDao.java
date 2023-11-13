package com.example.Proyecto.Models.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Proyecto.Models.Entity.Contratacion;

public interface IContratacionDao extends CrudRepository<Contratacion, Long> {

    @Query(value = "SELECT * FROM contratacion AS con WHERE con.gestion_contratacion = ?1", nativeQuery = true)
    public List<Contratacion> findByGestionContratacion(String gestionContratacion);

    @Query(value = "SELECT * FROM contratacion AS con INNER JOIN modalidad AS mo ON con.id_modalidad = mo.id_modalidad WHERE mo.nombre_modalidad = ?1", nativeQuery = true)
    public List<Contratacion> findByNombreModalidad(String nombreModalidad);

    @Query(value = "SELECT * FROM contratacion AS con INNER JOIN proyecto AS pr ON con.id_proyecto = pr.id_proyecto INNER JOIN unidad as uni ON pr.id_unidad = uni.id_unidad WHERE con.gestion_contratacion = ?1 AND uni.nombre_unidad = ?2", nativeQuery = true)
    public List<Contratacion> findByGestion_y_Unidad(String gestionContratacion, String nombreUnidad);

    @Query(value = "SELECT * FROM contratacion AS con INNER JOIN proyecto AS pr ON con.id_proyecto = pr.id_proyecto WHERE con.gestion_contratacion = ?1 AND pr.numero_proyecto = ?2", nativeQuery = true)
    public List<Contratacion> findByGestion_y_NumProyecto(String gestionContratacion, String numProyecto);

    @Query(value = "SELECT * FROM contratacion AS con INNER JOIN tipo_modalidad AS tp_mo ON con.id_tipo_modalidad = tp_mo.id_tipo_modalidad WHERE con.gestion_contratacion = ?1 AND tp_mo.nombre_tipo_modalidad = ?2", nativeQuery = true)
    public List<Contratacion> findByGestion_y_TipoModalidad(String gestionContratacion, String nomTipoModalidad);

    @Query(value = "SELECT * FROM contratacion AS con INNER JOIN persona AS per ON con.id_persona = per.id_persona WHERE con.gestion_contratacion = ?1 AND per.id_persona = ?2", nativeQuery = true)
    public List<Contratacion> findByGestion_y_Persona(String gestionContratacion, Long id_Persona);

    @Query(value = "SELECT * FROM contratacion AS con INNER JOIN modalidad AS mo ON con.id_modalidad = mo.id_modalidad WHERE con.gestion_contratacion = ?1 AND mo.nombre_modalidad = ?2", nativeQuery = true)
    public List<Contratacion> findByGestion_y_Modalidad(String gestionContratacion, String nombreModalidad);

    @Query(value = "SELECT * FROM contratacion AS con INNER JOIN proyecto AS pr ON con.id_proyecto = pr.id_proyecto INNER JOIN unidad as uni ON pr.id_unidad = uni.id_unidad \n" + //
            "INNER JOIN tipo_modalidad as tp_mo ON con.id_tipo_modalidad = tp_mo.id_tipo_modalidad \n" + //
            "WHERE con.gestion_contratacion = ?1 AND tp_mo.nombre_tipo_modalidad  = ?2 AND uni.nombre_unidad = ?3", nativeQuery = true)
    public List<Contratacion> findByGestion_TipoModalidad_Unidad(String gestionContratacion, String nomTipoModalidad, String nombreUnidad);

    @Query(value = "SELECT * FROM contratacion AS con INNER JOIN modalidad AS mo ON con.id_modalidad = mo.id_modalidad \n" + 
            "INNER JOIN tipo_modalidad AS tp_mo ON con.id_tipo_modalidad = tp_mo.id_tipo_modalidad \n" + //
            "WHERE con.gestion_contratacion = ?1 AND mo.nombre_modalidad  = ?2 AND tp_mo.nombre_tipo_modalidad = ?3", nativeQuery = true)
    public List<Contratacion> findByGestion_Modalidad_TipoModalidad(String gestionContratacion, String nombreModalidad, String nomTipoModalidad);

    @Query(value = "SELECT * FROM contratacion AS con INNER JOIN proyecto AS pr ON con.id_proyecto = pr.id_proyecto \n" + //
                    "INNER JOIN modalidad AS mo ON con.id_modalidad = mo.id_modalidad\n" + //
                    "WHERE  con.gestion_contratacion = ?1 AND mo.nombre_modalidad = ?2 AND pr.numero_proyecto = ?3", nativeQuery = true)
    public List<Contratacion> findByGestion_Modalidad_NumProyecto(String gestionContratacion, String nombreModalidad, String numProyecto);
}
