package com.example.Proyecto.Controller.UsuarioControllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Proyecto.Models.Entity.Persona;
import com.example.Proyecto.Models.Entity.Usuario;
import com.example.Proyecto.Models.IService.IPersonaService;
import com.example.Proyecto.Models.IService.IUsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IUsuarioService usuarioService;

    @RequestMapping(value = "UsuarioR", method = RequestMethod.GET)
    public String UsuarioR(@Validated Usuario usuario, Model model, HttpServletRequest request) throws Exception {

        if (request.getSession().getAttribute("persona") != null) {
            List<Persona> personas = personaService.findAll();
            List<Usuario> usuarios = usuarioService.findAll();
            model.addAttribute("reg", "true");
            model.addAttribute("personas", personas);
            model.addAttribute("usuarios", usuarios);

            return "usuario/usuario-adm";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "UsuarioF", method = RequestMethod.POST)
    public String UsuarioF(HttpServletRequest request, @Validated Usuario usuario) {

        usuario.setEstado("A");
        usuarioService.save(usuario);

        return "redirect:/UsuarioR";
    }

    @RequestMapping(value = "/editar-usuario/{id_usuario}")
    public String editar_usu(@PathVariable("id_usuario") String id_usuario, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("persona") != null) {
            try {
                Long id_usu = Long.parseLong(id_usuario);
                Usuario usuario = usuarioService.findOne(id_usu);
                model.addAttribute("usuario", usuario);

                List<Persona> personas = personaService.findAll();
                List<Usuario> usuarios = usuarioService.findAll();
                model.addAttribute("edit", "true");
                model.addAttribute("personas", personas);
                model.addAttribute("usuarios", usuarios);
                return "usuario/usuario-adm";

            } catch (Exception e) {

                return "redirect:/AdmPG";
            }
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/UsuarioModF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
    public String usuario_mod(HttpServletRequest request, @Validated Usuario usuario,
            RedirectAttributes redirectAttrs) { // validar los datos capturados (1)

        usuario.setEstado("A");
        usuarioService.save(usuario);
        return "redirect:/UsuarioR";
    }

    @RequestMapping(value = "/eliminar-usuario/{id_usuario}")
    public String eliminar_usuario(HttpServletRequest request, @PathVariable("id_usuario") String id_usuario)
            throws Exception {
        if (request.getSession().getAttribute("persona") != null) {
            try {
                Long id_usu = Long.parseLong(id_usuario);
                Usuario usuario = usuarioService.findOne(id_usu);
                usuario.setEstado("X");
                usuario.setContrasena(usuario.getContrasena());
                usuarioService.save(usuario);

                return "redirect:/UsuarioR";
            } catch (Exception e) {
                return "redirect:/AdmPG";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/tableUsuarios")
    public String tableUsuarios(@Validated Usuario usuario, Model model) throws Exception {

        List<Usuario> usuarios = usuarioService.findAll();

        model.addAttribute("usuarios", usuarios);

        return "usuario/tableFragment :: table";
    }

}
