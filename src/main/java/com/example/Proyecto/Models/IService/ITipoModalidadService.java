package com.example.Proyecto.Models.IService;

import java.util.List;

import com.example.Proyecto.Models.Entity.TipoModalidad;

public interface ITipoModalidadService {
    
     public List<TipoModalidad> findAll();

    public void save(TipoModalidad tipoModalidad);

    public TipoModalidad findOne(Long id);

    public void delete(Long id);
}
