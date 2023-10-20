package com.example.Proyecto.Controller.GradoControllers;

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

import com.example.Proyecto.Models.IService.IGradoService;



@Controller
public class GradoController {

    @Autowired
    private IGradoService gradoService;



  

    @RequestMapping(value = "GradoR", method = RequestMethod.GET)
    public String GradoR(@Validated Grado grado, Model model, HttpServletRequest request) throws Exception {

        if (request.getSession().getAttribute("persona") != null) {
            List<Grado> grados = gradoService.findAll();
           
           
            model.addAttribute("grados", grados);
        

            return "grado/grado-adm";
        } else {
            return "redirect:/";
        }
    }

  

    @RequestMapping(value = "GradoF", method = RequestMethod.POST)
    public String GradoF(HttpServletRequest request, @Validated Grado grado) { 

        grado.setEstado_grado("A");
        gradoService.save(grado);
        


        return "redirect:/GradoR";
    }
    

   @RequestMapping(value = "/editar-grado/{id_grado}")
    public String editar_grado(@PathVariable("id_grado") String id_grado, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("persona") != null) {
            try {
                Long id_gra = Long.parseLong(id_grado);
                Grado grado = gradoService.findOne(id_gra);
                
                model.addAttribute("grado", grado);

                List<Grado> grados = gradoService.findAll();
               
                model.addAttribute("grados", grados);
  
                return "grado/grado-adm";

            } catch (Exception e) {

                return "redirect:/AdmPG";
            }
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/GradoModF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
    public String tpgrado_mod(HttpServletRequest request, @Validated Grado grado,
            RedirectAttributes redirectAttrs) { // validar los datos capturados (1)

        //Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

         grado.setEstado_grado("A");
        gradoService.save(grado);
        return "redirect:/GradoR";
    }

    @RequestMapping(value = "/eliminar-grado/{id_grado}")
    public String eliminar_c(HttpServletRequest request, @PathVariable("id_grado") String id_grado)
            throws Exception {
        if (request.getSession().getAttribute("persona") != null) {
        try {
            Long id_gra = Long.parseLong(id_grado);
            Grado grado = gradoService.findOne(id_gra);

            grado.setEstado_grado("X");
            gradoService.save(grado);
            
            return "redirect:/GradoR";
        } catch (Exception e) {
            return "redirect:/AdmPG";
        }
        } else {
            return "redirect:/";
        }
    }
    

    @GetMapping("/tableGrados")
    public String tableGrados(@Validated Grado grado, Model model) throws Exception {

        List<Grado> grados = gradoService.findAll();
 
        model.addAttribute("grados", grados);


        return "grado/tableFragment :: table";
    }
    

}
