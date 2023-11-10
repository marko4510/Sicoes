package com.example.Proyecto.Controller.ReporteControllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Proyecto.Models.IService.IContratacionService;
import com.example.Proyecto.Models.IService.IModalidadService;
import com.example.Proyecto.Models.IService.IPersonaService;
import com.example.Proyecto.Models.IService.IProyectoService;
import com.example.Proyecto.Models.IService.IUnidadService;

@Controller
public class ReporteControllers {

    @Autowired
    private IModalidadService modalidadService;

    @Autowired
    private IContratacionService contratacionService;

    @Autowired
    private IProyectoService proyectoService;

    @Autowired
    private IUnidadService unidadService;

    @Autowired
    private IPersonaService personaService;
    
    @RequestMapping(value = "ReporteCon", method = RequestMethod.GET)
    public String PlantillaBasia(HttpServletRequest request, Model model) {

        if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("modalidales", modalidadService.findAll());
            model.addAttribute("proyectos", proyectoService.findAll());
            model.addAttribute("unidades", unidadService.findAll());
            
            return "reporte/generar-reporte";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reportG",method = RequestMethod.POST)
    public String Mostrar_Tabla_Reporte_Gestion(@RequestParam("gestion1")String gestion,HttpServletRequest request, Model model){

        if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("contrataciones", contratacionService.findByGestionContratacion(gestion));
            
            return "reporte/tabla-reporte";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reportM",method = RequestMethod.POST)
    public String Mostrar_Tabla_Reporte_Modalidad(@RequestParam("nombre_modalidad")String nombre_modalidad,HttpServletRequest request, Model model){

        if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("contrataciones", contratacionService.findByNombreModalidad(nombre_modalidad));
            
            return "reporte/tabla-reporte";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reportGM",method = RequestMethod.POST)
    public String Mostrar_Tabla_Reporte_Gestion_y_Modalidad(@RequestParam("gestion2")String nombre_modalidad,HttpServletRequest request, Model model){

        if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("contrataciones", contratacionService.findByNombreModalidad(nombre_modalidad));
            
            return "reporte/tabla-reporte";
        } else {
            return "redirect:/";
        }
    }

}
