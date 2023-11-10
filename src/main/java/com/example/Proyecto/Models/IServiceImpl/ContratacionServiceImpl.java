package com.example.Proyecto.Models.IServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyecto.Models.Dao.IContratacionDao;
import com.example.Proyecto.Models.Entity.Contratacion;
import com.example.Proyecto.Models.IService.IContratacionService;


@Service
public class ContratacionServiceImpl implements IContratacionService{

    @Autowired
    private IContratacionDao contratacionDao;

    @Override
    public List<Contratacion> findAll() {
        return (List<Contratacion>) contratacionDao.findAll();
    }

    @Override
    public void save(Contratacion contratacion) {
        contratacionDao.save(contratacion);
    }

    @Override
    public Contratacion findOne(Long id) {
        return contratacionDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        contratacionDao.deleteById(id);
    }

    @Override
    public List<Contratacion> findByGestionContratacion(String gestionContratacion) {
        // TODO Auto-generated method stub
        return contratacionDao.findByGestionContratacion(gestionContratacion);
    }

    @Override
    public List<Contratacion> findByNombreModalidad(String nombreModalidad) {
        // TODO Auto-generated method stub
        return contratacionDao.findByNombreModalidad(nombreModalidad);
    }

    @Override
    public List<Contratacion> findByGestion_y_Unidad(String gestionContratacion, String nombreUnidad) {
        // TODO Auto-generated method stub
        return contratacionDao.findByGestion_y_Unidad(gestionContratacion, nombreUnidad);
    }

    @Override
    public List<Contratacion> findByGestion_y_NumProyecto(String gestionContratacion, String numProyecto) {
        // TODO Auto-generated method stub
        return contratacionDao.findByGestion_y_NumProyecto(gestionContratacion, numProyecto);
    }

    @Override
    public List<Contratacion> findByGestion_y_TipoModalidad(String gestionContratacion, String nomTipoModalidad) {
        // TODO Auto-generated method stub
        return contratacionDao.findByGestion_y_TipoModalidad(gestionContratacion, nomTipoModalidad);
    }

    @Override
    public List<Contratacion> findByGestion_y_Persona(String gestionContratacion, String nomPersona) {
        // TODO Auto-generated method stub
        return contratacionDao.findByGestion_y_Persona(gestionContratacion, nomPersona);
    }
    
}
