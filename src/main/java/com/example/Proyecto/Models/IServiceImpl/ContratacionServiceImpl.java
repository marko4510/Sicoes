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
    public List<Contratacion> findByGestion_y_Modalidad(String gestionContratacion, String nombreModalidad) {
        // TODO Auto-generated method stub
        return contratacionDao.findByGestion_y_Modalidad(gestionContratacion, nombreModalidad);
    }

    @Override
    public List<Contratacion> findByGestion_y_Persona(String gestionContratacion, Long id_Persona) {
        // TODO Auto-generated method stub
        return contratacionDao.findByGestion_y_Persona(gestionContratacion, id_Persona);
    }

    @Override
    public List<Contratacion> findByGestion_TipoModalidad_Unidad(String gestionContratacion, String nomTipoModalidad,
            String nombreUnidad) {
        // TODO Auto-generated method stub
        return contratacionDao.findByGestion_TipoModalidad_Unidad(gestionContratacion, nomTipoModalidad, nombreUnidad);
    }

    @Override
    public List<Contratacion> findByGestion_Modalidad_TipoModalidad(String gestionContratacion, String nombreModalidad,
            String nomTipoModalidad) {
        // TODO Auto-generated method stub
        return contratacionDao.findByGestion_Modalidad_TipoModalidad(gestionContratacion, nombreModalidad, nomTipoModalidad);
    }

    @Override
    public List<Contratacion> findByGestion_Modalidad_NumProyecto(String gestionContratacion, String nombreModalidad,
            String numProyecto) {
        // TODO Auto-generated method stub
        return contratacionDao.findByGestion_Modalidad_NumProyecto(gestionContratacion, nombreModalidad, numProyecto);
    }
    
}
