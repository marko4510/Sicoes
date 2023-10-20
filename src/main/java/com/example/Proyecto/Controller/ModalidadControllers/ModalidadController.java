package com.example.Proyecto.Controller.ModalidadControllers;

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

import com.example.Proyecto.Models.Entity.Modalidad;
import com.example.Proyecto.Models.Entity.TipoModalidad;
import com.example.Proyecto.Models.IService.IModalidadService;
import com.example.Proyecto.Models.IService.ITipoModalidadService;


@Controller
public class ModalidadController {


    @Autowired
    private IModalidadService modalidadService;

    @Autowired
    private ITipoModalidadService tipoModalidadService;

     @RequestMapping(value = "ModalidadR", method = RequestMethod.GET)
    public String ModalidadR(@Validated Modalidad Modalidad, Model model, HttpServletRequest request) throws Exception {

        if (request.getSession().getAttribute("persona") != null) {
             List<Modalidad> Modalidades = modalidadService.findAll();
             List<TipoModalidad> tipoModalidades = tipoModalidadService.findAll();
            
    
             model.addAttribute("tipoModalidades", tipoModalidades);
         
           
           
            model.addAttribute("Modalidades", Modalidades);
        

            return "modalidad/modalidad-adm";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "ModalidadF", method = RequestMethod.POST)
    public String ModalidadF(HttpServletRequest request, @Validated Modalidad modalidad) { 
        modalidad.setEstado_modalidad("A");
        modalidadService.save(modalidad);
        
        
        
        


        return "redirect:/ModalidadR";
    }

     @RequestMapping(value = "/editar-modalidad/{id_modalidad}")
    public String editar_modalidad(@PathVariable("id_modalidad") String id_modalidad, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("persona") != null) {
            try {

                Long id_mod = Long.parseLong(id_modalidad);
                Modalidad modalidad = modalidadService.findOne(id_mod);
         
                
                model.addAttribute("modalidad", modalidad);

                List<TipoModalidad> tipoModalidades = tipoModalidadService.findAll();
                model.addAttribute("tipoModalidades", tipoModalidades);
                
                List<Modalidad> modalidades = modalidadService.findAll();
               
                model.addAttribute("modalidades", modalidades);
  
                return "modalidad/modalidad-adm";

            } catch (Exception e) {

                return "redirect:/AdmPG";
            }
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/modalidadModF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
    public String tpmodalidad_mod(HttpServletRequest request, @Validated Modalidad modalidad,
            RedirectAttributes redirectAttrs) { // validar los datos capturados (1)

        //Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        modalidad.setEstado_modalidad("A");
        modalidadService.save(modalidad);
        
        
        return "redirect:/ModalidadR";
    }

    @RequestMapping(value = "/eliminar-modalidad/{id_modalidad}")
    public String eliminar_tpmodalidad(HttpServletRequest request, @PathVariable("id_modalidad") String id_modalidad)
            throws Exception {
        if (request.getSession().getAttribute("persona") != null) {
        try {
            Long id_mod = Long.parseLong(id_modalidad);
            Modalidad modalidad = modalidadService.findOne(id_mod);
            
           modalidad.setEstado_modalidad("X");
        modalidadService.save(modalidad);
     
            
            
            return "redirect:/modalidadR";
        } catch (Exception e) {
            return "redirect:/AdmPG";
        }
        } else {
            return "redirect:/";
        }
    }

      @GetMapping("/tableModalidad")
    public String tableModalidad(@Validated Modalidad modalidad, Model model) throws Exception {

        List<Modalidad> modalidades = modalidadService.findAll();
 
        model.addAttribute("modalidades", modalidades);


        return "modalidad/tableFragment :: table";
    }

}
