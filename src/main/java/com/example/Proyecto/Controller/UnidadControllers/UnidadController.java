package com.example.Proyecto.Controller.UnidadControllers;
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

import com.example.Proyecto.Models.Entity.Grado;
import com.example.Proyecto.Models.Entity.Unidad;
import com.example.Proyecto.Models.IService.IGradoService;
import com.example.Proyecto.Models.IService.IUnidadService;

@Controller
public class UnidadController {
    
    @Autowired
    private IUnidadService unidadService;

    @RequestMapping(value = "UnidadR", method = RequestMethod.GET)
    public String UnidadR(@Validated Unidad unidad, Model model, HttpServletRequest request) throws Exception {

        if (request.getSession().getAttribute("persona") != null) {
             List<Unidad> unidades = unidadService.findAll();
            
           
           
            model.addAttribute("unidades", unidades);
        

            return "unidad/unidad-adm";
        } else {
            return "redirect:/";
        }
    }

  

    @RequestMapping(value = "UnidadF", method = RequestMethod.POST)
    public String UnidadF(HttpServletRequest request, @Validated Unidad unidad) { 
        unidad.setEstado_unidad("A");
        unidadService.save(unidad);
        
        


        return "redirect:/UnidadR";
    }
    

   @RequestMapping(value = "/editar-unidad/{id_unidad}")
    public String editar_unidad(@PathVariable("id_unidad") String id_unidad, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("persona") != null) {
            try {
                Long id_uni = Long.parseLong(id_unidad);
                Unidad unidad = unidadService.findOne(id_uni);
                
                model.addAttribute("unidad", unidad);

                List<Unidad> unidades = unidadService.findAll();
               
                model.addAttribute("unidades", unidades);
  
                return "unidad/unidad-adm";

            } catch (Exception e) {

                return "redirect:/AdmPG";
            }
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/UnidadModF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
    public String tpunidad_mod(HttpServletRequest request, @Validated Unidad unidad,
            RedirectAttributes redirectAttrs) { // validar los datos capturados (1)

        //Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

         unidad.setEstado_unidad("A");
        unidadService.save(unidad);
        
        return "redirect:/UnidadR";
    }

    @RequestMapping(value = "/eliminar-unidad/{id_unidad}")
    public String eliminar_c(HttpServletRequest request, @PathVariable("id_unidad") String id_unidad)
            throws Exception {
        if (request.getSession().getAttribute("persona") != null) {
        try {
            Long id_uni = Long.parseLong(id_unidad);
            Unidad unidad = unidadService.findOne(id_uni);
           
            
            unidad.setEstado_unidad("X");
            unidadService.save(unidad);
            
            
            return "redirect:/UnidadR";
        } catch (Exception e) {
            return "redirect:/AdmPG";
        }
        } else {
            return "redirect:/";
        }
    }
    

    @GetMapping("/tableUnidad")
    public String tableUnidad(@Validated Unidad unidad, Model model) throws Exception {

        List<Unidad> unidades = unidadService.findAll();
 
        model.addAttribute("unidades", unidades);


        return "grado/tableFragment :: table";
    }
}









    


