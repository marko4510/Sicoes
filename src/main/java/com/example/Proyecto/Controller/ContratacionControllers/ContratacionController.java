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
import com.example.Proyecto.Models.IService.IContratacionService;

@Controller
public class ContratacionController {

    @Autowired
    private IContratacionService contratacionService;

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
}
