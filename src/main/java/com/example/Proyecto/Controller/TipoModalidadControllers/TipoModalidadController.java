package com.example.Proyecto.Controller.TipoModalidadControllers;

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

import com.example.Proyecto.Models.Entity.TipoModalidad;
import com.example.Proyecto.Models.IService.ITipoModalidadService;


@Controller
public class TipoModalidadController {

    @Autowired
    private ITipoModalidadService tipoModalidadService;

    @RequestMapping(value = "TipoModalidadR", method = RequestMethod.GET)
    public String TipoModalidadR(@Validated TipoModalidad tipoModalidad, Model model, HttpServletRequest request) throws Exception {

        if (request.getSession().getAttribute("persona") != null) {
             List<TipoModalidad> tipoModalidades = tipoModalidadService.findAll();
            
           
             model.addAttribute("reg", "true");
            model.addAttribute("tipoModalidades", tipoModalidades);
        

            return "tipomodalidad/tipomodalidad-adm";
        } else {
            return "redirect:/";
        }
    }


    @RequestMapping(value = "TipoModalidadF", method = RequestMethod.POST)
    public String TipoModalidadF(HttpServletRequest request, @Validated TipoModalidad tipoModalidad) { 
        
        tipoModalidad.setEstado_tipo_modalidad("A");
        tipoModalidadService.save(tipoModalidad);
        
        
        


        return "redirect:/TipoModalidadR";
    }

    @RequestMapping(value = "/editar-tpmodalidad/{id_tipo_modalidad}")
    public String editar_tpmodalidad(@PathVariable("id_tipo_modalidad") String id_tipo_modalidad, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("persona") != null) {
            try {

                Long id_tpm = Long.parseLong(id_tipo_modalidad);
                TipoModalidad tipoModalidad = tipoModalidadService.findOne(id_tpm);
         
                
                model.addAttribute("tipoModalidad", tipoModalidad);

                List<TipoModalidad> tipoModalidades= tipoModalidadService.findAll();
                model.addAttribute("edit", "true");
                model.addAttribute("tipoModalidades", tipoModalidades);
  
                return "tipomodalidad/tipomodalidad-adm";

            } catch (Exception e) {

                return "redirect:/AdmPG";
            }
        } else {
            return "redirect:/";
        }

    }

     @RequestMapping(value = "/TpModalidadModF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
    public String tpmodalidad_mod(HttpServletRequest request, @Validated TipoModalidad tipoModalidad,
            RedirectAttributes redirectAttrs) { // validar los datos capturados (1)

        //Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        tipoModalidad.setEstado_tipo_modalidad("A");
        tipoModalidadService.save(tipoModalidad);
        
        
        return "redirect:/TipoModalidadR";
    }


    @RequestMapping(value = "/eliminar-tpmodalidad/{id_tipo_modalidad}")
    public String eliminar_tpmodalidad(HttpServletRequest request, @PathVariable("id_tipo_modalidad") String id_tipo_modalidad)
            throws Exception {
        if (request.getSession().getAttribute("persona") != null) {
        try {
            Long id_tpm = Long.parseLong(id_tipo_modalidad);
            TipoModalidad tipoModalidad = tipoModalidadService.findOne(id_tpm);
            
           tipoModalidad.setEstado_tipo_modalidad("X");
            tipoModalidadService.save(tipoModalidad);
     
            
            
            return "redirect:/TipoModalidadR";
        } catch (Exception e) {
            return "redirect:/AdmPG";
        }
        } else {
            return "redirect:/";
        }
    }

      @GetMapping("/tableTiposModalidad")
    public String tabletiposModalidad(@Validated TipoModalidad tipoModalidad, Model model) throws Exception {

        List<TipoModalidad> tipoModalidades = tipoModalidadService.findAll();
 
        model.addAttribute("tipoModalidades", tipoModalidades);


        return "tipomodalidad/tableFragment :: table";
    }
}
