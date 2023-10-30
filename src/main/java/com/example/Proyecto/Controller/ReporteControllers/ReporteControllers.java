package com.example.Proyecto.Controller.ReporteControllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReporteControllers {
    
    @RequestMapping(value = "ReporteCon", method = RequestMethod.GET)
    public String PlantillaBasia(HttpServletRequest request, Model model) {

        if (request.getSession().getAttribute("persona") != null) {



            return "reporte/generar-reporte";
        } else {
            return "redirect:/";
        }
    }
}
