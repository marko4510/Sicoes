package com.example.Proyecto.Models.IServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyecto.Models.Dao.IUsuarioDao;
import com.example.Proyecto.Models.Entity.Usuario;
import com.example.Proyecto.Models.IService.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    public void save(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public Usuario findOne(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        usuarioDao.deleteById(id);
    }

    @Override
    public Long insertar_adm(String usuario_nom, String contrasena, Integer id_persona, Integer id_consejo) {
        return usuarioDao.insertar_adm(usuario_nom, contrasena, id_persona, id_consejo);
    }

    @Override
    public Long validar_adm(String usuario_nom, String contrasena) {
        return usuarioDao.validar_adm(usuario_nom, contrasena);
    }

    @Override
    public Usuario getUsuarioContraseña(String correo, String password) {
        return usuarioDao.getUsuarioContraseña(correo, password);
    }

    @Override
    public Usuario getUsuarioContraseña2(String correo, String password) {
        return usuarioDao.getUsuarioContraseña2(correo, password);
    }

    
}
