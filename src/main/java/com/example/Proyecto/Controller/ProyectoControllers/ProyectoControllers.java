package com.example.Proyecto.Controller.ProyectoControllers;

import java.util.ArrayList;
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

import com.example.Proyecto.Models.Entity.Proyecto;
import com.example.Proyecto.Models.Entity.Usuario;
import com.example.Proyecto.Models.IService.IProyectoService;
import com.example.Proyecto.Models.IService.IUnidadService;
import com.example.Proyecto.Models.Otros.Encryptar;

@Controller
@RequestMapping("/adm")
public class ProyectoControllers {
    @Autowired
    private IProyectoService proyectoService;

    @Autowired
    private IUnidadService unidadService;

    @RequestMapping(value = "ProyectoR", method = RequestMethod.GET)
    public String ProyectoR(HttpServletRequest request, @Validated Proyecto tipoResolucion, Model model)
            throws Exception {
        if (request.getSession().getAttribute("persona") != null) {
            List<Proyecto> proyectos = proyectoService.findAll();
            List<String> encryptedIds = new ArrayList<>();
            for (Proyecto proyecto2 : proyectos) {
                String id_encryptado = Encryptar.encrypt(Long.toString(proyecto2.getId_proyecto()));
                encryptedIds.add(id_encryptado);
            }
            model.addAttribute("proyectos", proyectoService.findAll());
            model.addAttribute("unidades", unidadService.findAll());
            model.addAttribute("id_encryptado", encryptedIds);

            return "proyecto/gestionar-proyecto";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/ProyectoF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
    public String ProyectoF(@Validated Proyecto proyecto) { // validar los datos capturados (1)

        proyecto.setEstado_proyecto("A");
        proyectoService.save(proyecto);
        return "redirect:/adm/ProyectoR";
    }

    @RequestMapping(value = "/editar-proyecto/{id_proyecto}")
    public String editar_p(@PathVariable("id_proyecto") String id_proyecto, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("persona") != null) {
            try {
                Long id_proye = Long.parseLong(Encryptar.decrypt(id_proyecto));
                Proyecto proyecto = proyectoService.findOne(id_proye);
                model.addAttribute("proyecto", proyecto);

                List<Proyecto> proyectos = proyectoService.findAll();
                List<String> encryptedIds = new ArrayList<>();
                for (Proyecto proyecto2 : proyectos) {
                    String id_encryptado = Encryptar.encrypt(Long.toString(proyecto2.getId_proyecto()));
                    encryptedIds.add(id_encryptado);
                }
                model.addAttribute("proyectos", proyectos);
                model.addAttribute("unidades", unidadService.findAll());
                model.addAttribute("id_encryptado", encryptedIds);
                return "proyecto/gestionar-proyecto";

            } catch (Exception e) {

                return "redirect:/AdmPG";
            }
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/ProyectoModF", method = RequestMethod.POST)
    public String proyectoModF(@Validated Proyecto proyecto, RedirectAttributes redirectAttrs) {

        proyecto.setEstado_proyecto("A");
        proyectoService.save(proyecto);
        return "redirect:/adm/ProyectoR";
    }


    @RequestMapping(value = "/eliminar-proyecto/{id_proyecto}")
    public String eliminar_c(HttpServletRequest request, @PathVariable("id_proyecto") String id_proyecto)
            throws Exception {
        if (request.getSession().getAttribute("persona") != null) {
            try {
                Long id_proye = Long.parseLong(Encryptar.decrypt(id_proyecto));
                Proyecto proyecto = proyectoService.findOne(id_proye);
                proyecto.setEstado_proyecto("X");
                proyectoService.save(proyecto);
                return "redirect:/adm/ProyectoR";
            } catch (Exception e) {
                return "redirect:/AdmPG";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/tableProyecto")
    public String tableRequisitos(@Validated Proyecto proyecto, Model model) throws Exception {

        List<Proyecto> proyectos = proyectoService.findAll();
        List<String> encryptedIds = new ArrayList<>();
        for (Proyecto proyecto2 : proyectos) {
            String id_encryptado = Encryptar.encrypt(Long.toString(proyecto2.getId_proyecto()));
            encryptedIds.add(id_encryptado);
        }
        model.addAttribute("proyectos", proyectos);
        model.addAttribute("id_encryptado", encryptedIds);

        return "proyecto/tabla-proyecto :: table";
    }

}
