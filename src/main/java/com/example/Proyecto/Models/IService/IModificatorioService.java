package com.example.Proyecto.Models.IService;

import java.util.List;

import com.example.Proyecto.Models.Entity.Modificatorio;

public interface IModificatorioService {

    public List<Modificatorio> findAll();

    public void save(Modificatorio modificatorio);

    public Modificatorio findOne(Long id);

    public void delete(Long id);

    public Long cantidadModificatorio(Long id_modificatorio);
}
