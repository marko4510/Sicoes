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

@Controller
public class ReporteControllers {

    @Autowired
    private IModalidadService modalidadService;

    @Autowired
    private IContratacionService contratacionService;
    
    @RequestMapping(value = "ReporteCon", method = RequestMethod.GET)
    public String PlantillaBasia(HttpServletRequest request, Model model) {

        if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("modalidales", modalidadService.findAll());
            
            return "reporte/generar-reporte";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reportG",method = RequestMethod.POST)
    public String Mostrar_Tabla_Reporte(@RequestParam("gestion")String gestion,HttpServletRequest request, Model model){

        if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("contrataciones", contratacionService.findByGestionContratacion(gestion));
            
            return "reporte/tabla-reporte";
        } else {
            return "redirect:/";
        }
    }
}
