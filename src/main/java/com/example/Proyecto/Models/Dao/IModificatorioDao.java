package com.example.Proyecto.Models.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Proyecto.Models.Entity.Contratacion;
import com.example.Proyecto.Models.Entity.Modificatorio;

public interface IModificatorioDao extends CrudRepository<Modificatorio, Long>{
    
    @Query(value = "SELECT COUNT(*) AS cantidad_modificatorios FROM modificatorio WHERE id_contratacion = ?1", nativeQuery = true)
    public Long cantidadModificatorio(Long id_modificatorio);
}
