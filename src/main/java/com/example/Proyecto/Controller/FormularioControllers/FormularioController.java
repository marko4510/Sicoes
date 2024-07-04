package com.example.Proyecto.Controller.FormularioControllers;

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

import com.example.Proyecto.Models.Entity.Formulario;
import com.example.Proyecto.Models.Entity.Grado;
import com.example.Proyecto.Models.IService.IFormularioService;


@Controller
public class FormularioController {

    @Autowired
    private IFormularioService formularioService;

    
    @RequestMapping(value = "FormularioR", method = RequestMethod.GET)
    public String FormularioR(@Validated Formulario formulario, Model model, HttpServletRequest request) throws Exception {

        if (request.getSession().getAttribute("persona") != null) {
            List<Formulario> formularios = formularioService.findAll();
           
            model.addAttribute("reg", "true");
            model.addAttribute("formularios", formularios);
        

            return "formulario/formulario-adm";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "FormularioF", method = RequestMethod.POST)
    public String FormularioF(HttpServletRequest request, @Validated Formulario formulario) { 
        formulario.setEstado_formulario("A");
        formularioService.save(formulario);

        return "redirect:/FormularioR";
    }

    @RequestMapping(value = "/editar-formulario/{id_formulario}")
    public String editar_formulario(@PathVariable("id_formulario") String id_formulario, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("persona") != null) {
            try {
                Long id_for = Long.parseLong(id_formulario);
                Formulario formulario = formularioService.findOne(id_for);
 
                
                model.addAttribute("formulario", formulario);

                List<Formulario> formularios = formularioService.findAll();
                model.addAttribute("edit", "true");
                model.addAttribute("formularios", formularios);
  
                return "formulario/formulario-adm";

            } catch (Exception e) {

                return "redirect:/AdmPG";
            }
        } else {
            return "redirect:/";
        }

    }

      @RequestMapping(value = "/FormularioModF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
    public String formulario_mod(HttpServletRequest request, @Validated Formulario formulario,
            RedirectAttributes redirectAttrs) { // validar los datos capturados (1)

        //Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        formulario.setEstado_formulario("A");
        formularioService.save(formulario);
        
        return "redirect:/FormularioR";
    }

    @RequestMapping(value = "/eliminar-formulario/{id_formulario}")
    public String eliminar_fc(HttpServletRequest request, @PathVariable("id_formulario") String id_formulario)
            throws Exception {
        if (request.getSession().getAttribute("persona") != null) {
        try {
            Long id_for = Long.parseLong(id_formulario);
            Formulario formulario = formularioService.findOne(id_for);
            
            formulario.setEstado_formulario("X");
            formularioService.save(formulario);

            
            return "redirect:/FormularioR";
        } catch (Exception e) {
            return "redirect:/AdmPG";
        }
        } else {
            return "redirect:/";
        }
    }


      @GetMapping("/tableFormularios")
    public String tableFormulario(@Validated Formulario formulario, Model model) throws Exception {

        List<Formulario> formularios = formularioService.findAll();
 
        model.addAttribute("formularios", formularios);


        return "formulario/tableFragment :: table";
    }
    
}
