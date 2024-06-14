package com.example.Proyecto.Models.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import com.example.Proyecto.Models.Entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {


    @Procedure(procedureName = "insertar_adm")
    public Long insertar_adm(String usuario_nom, String contrasena, Integer id_persona, Integer id_consejo);

    @Procedure(procedureName = "validar_adm")
    public Long validar_adm(String usuario_nom, String contrasena);

    @Query(value = "select * from usuario u where u.usuario_nom = ?1 and u.usuario_codigo = ?2", nativeQuery = true)
    public Usuario getUsuarioContraseña(String correo, String password);

    @Query(value = "select * from usuario u where u.usuario_nom = ?1 and u.contrasena = ?2", nativeQuery = true)
    public Usuario getUsuarioContraseña2(String correo, String password);
}
