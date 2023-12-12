package com.example.Proyecto.Models.Dao;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.Proyecto.Models.Entity.ArchivoAdjunto;

@Repository("IArchivoAdjuntoDao")
public class IArchivoAdjuntoDaoImpl implements IArchivoAdjuntoDao{

    @PersistenceContext
    private javax.persistence.EntityManager em;

    @Transactional

    @Override
    public ArchivoAdjunto registrarArchivoAdjunto(ArchivoAdjunto archivoAdjunto) {
        em.persist(archivoAdjunto);
        return archivoAdjunto;
    }

    @Override
    public ArchivoAdjunto buscarArchivoAdjunto(Long id_archivo_adjunto) {
        String sql = " SELECT arc "
        + " FROM ArchivoAdjunto arc "
        + " WHERE arc.id_archivo_adjunto =:id_archivo_adjunto"
        + " AND arc.estado_archivo_Adjunto = 'A' ";
        Query q = em.createQuery(sql);
        q.setParameter("id_archivo_adjunto", id_archivo_adjunto);
        try {
        return (ArchivoAdjunto) q.getSingleResult();
        } catch (Exception e) {
        return null;
        }
    }

    @Override
    public ArchivoAdjunto buscarArchivoAdjuntoPorConvenio(Long id_convenio) {

        String sql = "SELECT gaa  "
        + " FROM Convenio tr LEFT JOIN  tr.archivoAdjunto gaa"
        + " WHERE tr.id_convenio =:id_convenio "
        + " AND gaa.estado_archivo_adjunto = 'A' ";
        /*String sql = "select gaa from pasarela_tramite tr, pasarela_archivo_adjunto ar WHERE tr.id_archivo_adjunto=ar.id_archivo_adjunto and tr.estado='A' and tr.id_tramite=:id_tramite;";*/
        Query q = em.createQuery(sql);
        q.setParameter("id_convenio", id_convenio);
        try {
        return (ArchivoAdjunto) q.getSingleResult();
        } catch (Exception e) {
        return null;
        }
    }

     @Override
    public ArchivoAdjunto buscarArchivoAdjuntoPorResolucion(Long id_resolucion) {

        String sql = "SELECT gaa  "
        + " FROM Resolucion tr LEFT JOIN  tr.archivoAdjunto gaa"
        + " WHERE tr.id_resolucion =:id_resolucion "
        + " AND gaa.estado_archivo_adjunto = 'A' ";
        /*String sql = "select gaa from pasarela_tramite tr, pasarela_archivo_adjunto ar WHERE tr.id_archivo_adjunto=ar.id_archivo_adjunto and tr.estado='A' and tr.id_tramite=:id_tramite;";*/
        Query q = em.createQuery(sql);
        q.setParameter("id_resolucion", id_resolucion);
        try {
        return (ArchivoAdjunto) q.getSingleResult();
        } catch (Exception e) {
        return null;
        }
    }

    @Override
    public void modificarArchivoAdjunto(ArchivoAdjunto archivoAdjunto) {
        em.merge(archivoAdjunto);
    }

    @Override
    public List<ArchivoAdjunto> listarArchivoAdjuntoJPQL() {
        String sql = "SELECT adj "
        + " FROM ArchivoAdjunto adj "
        + " WHERE adj.estado_archivo_adjunto = 'A' ";
        Query q = em.createQuery(sql);
        return q.getResultList();
    }

    @Override
    public ArchivoAdjunto buscarArchivoAdjuntoPorContratacion(Long id_contratacion) {
         String sql = "SELECT gaa  "
        + " FROM Contratacion tr LEFT JOIN  tr.archivoAdjunto gaa"
        + " WHERE tr.id_contratacion =:id_contratacion "
        + " AND gaa.estado_archivo_adjunto = 'A' ";
        /*String sql = "select gaa from pasarela_tramite tr, pasarela_archivo_adjunto ar WHERE tr.id_archivo_adjunto=ar.id_archivo_adjunto and tr.estado='A' and tr.id_tramite=:id_tramite;";*/
        Query q = em.createQuery(sql);
        q.setParameter("id_contratacion", id_contratacion);
        try {
        return (ArchivoAdjunto) q.getSingleResult();
        } catch (Exception e) {
        return null;
        }
    }

    @Override
    public ArchivoAdjunto buscarArchivoAdjuntoPorModificatorio(Long id_modificatorio) {
         String sql = "SELECT gaa  "
        + " FROM Modificatorio tr LEFT JOIN  tr.archivoAdjunto gaa"
        + " WHERE tr.id_modificatorio =:id_modificatorio"
        + " AND gaa.estado_archivo_adjunto = 'A' ";
        /*String sql = "select gaa from pasarela_tramite tr, pasarela_archivo_adjunto ar WHERE tr.id_archivo_adjunto=ar.id_archivo_adjunto and tr.estado='A' and tr.id_tramite=:id_tramite;";*/
        Query q = em.createQuery(sql);
        q.setParameter("id_modificatorio", id_modificatorio);
        try {
        return (ArchivoAdjunto) q.getSingleResult();
        } catch (Exception e) {
        return null;
        }
    }
    
     }
