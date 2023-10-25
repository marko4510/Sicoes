package com.example.Proyecto.Controller.ContratacionControllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Proyecto.Models.Entity.Contratacion;
import com.example.Proyecto.Models.Entity.Usuario;
import com.example.Proyecto.Models.IService.IContratacionService;
import com.example.Proyecto.Models.IService.IFormularioService;
import com.example.Proyecto.Models.IService.IGradoService;
import com.example.Proyecto.Models.IService.IModalidadService;
import com.example.Proyecto.Models.IService.IPersonaService;
import com.example.Proyecto.Models.IService.IProyectoService;
import com.example.Proyecto.Models.IService.ITipoModalidadService;

@Controller
public class ContratacionController {

    @Autowired
    private IContratacionService contratacionService;

    @Autowired
    private IProyectoService proyectoService;

    @Autowired
    private IModalidadService modalidadService;

    @Autowired
    private ITipoModalidadService tipoModalidadService;

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IGradoService gradoService;

    @Autowired
    private IFormularioService formularioService;

    @RequestMapping(value = "ContratacionR", method = RequestMethod.GET)
    public String ContratacionR(@Validated Contratacion contratacion, Model model, HttpServletRequest request) throws Exception {

        if (request.getSession().getAttribute("persona") != null) {
            List<Contratacion> contrataciones = contratacionService.findAll();
           
           
            model.addAttribute("contrataciones", contrataciones);
        

            return "contratacion/contratacion-list";
        } else {
            return "redirect:/";
        }
    }

     @RequestMapping(value = "ContratacionForm", method = RequestMethod.GET)
    public String ContratacionForm(HttpServletRequest request, @Validated Contratacion contratacion, Model model)
            throws Exception {
        if (request.getSession().getAttribute("usuario") != null) {
      
            List<Contratacion> contrataciones = contratacionService.findAll();

            model.addAttribute("contrataciones", contrataciones);
            model.addAttribute("contratacion", new Contratacion());
            model.addAttribute("proyectos", proyectoService.findAll());
            model.addAttribute("modalidades", modalidadService.findAll());
            model.addAttribute("tmodalidades", tipoModalidadService.findAll());
            model.addAttribute("personas", personaService.findAll());
            model.addAttribute("grados", gradoService.findAll());
            model.addAttribute("formularios", formularioService.findAll());
        
            return "contratacion/contratacion-form";

        } else {
            return "redirect:/";
        }

    }
}
