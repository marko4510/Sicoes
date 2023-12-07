package com.example.Proyecto.Controller.ModificatorioControllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Proyecto.Models.Entity.Contratacion;
import com.example.Proyecto.Models.Entity.Modificatorio;
import com.example.Proyecto.Models.IService.IContratacionService;
import com.example.Proyecto.Models.IService.IFormularioService;
import com.example.Proyecto.Models.IService.IGradoService;
import com.example.Proyecto.Models.IService.IModificatorioService;
import com.example.Proyecto.Models.IService.IPersonaService;
import com.example.Proyecto.Models.IService.IProyectoService;
import com.example.Proyecto.Models.IService.ITipoModalidadService;

@Controller
public class ModificatorioController {

    @Autowired
    private IModificatorioService modificatorioService;

    @Autowired
    private IContratacionService contratacionService;

    @Autowired
    private IProyectoService proyectoService;

    @Autowired
    private ITipoModalidadService tipoModalidadService;

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IGradoService gradoService;

    @Autowired
    private IFormularioService formularioService;

    @RequestMapping(value = "ModificatorioInicio", method = RequestMethod.GET)
    public String ModificatorioInicio(HttpServletRequest request, @Validated Modificatorio modificatorio, Model model)
            throws Exception {
        if (request.getSession().getAttribute("usuario") != null) {

            List<Modificatorio> modificatorios = modificatorioService.findAll();

            model.addAttribute("modificatorios", modificatorios);
            model.addAttribute("modificatorio", new Modificatorio());
            model.addAttribute("contrataciones", contratacionService.findAll());

            return "modificatorio/gestionar-modificatorio";

        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/previa-modificatorio", method = RequestMethod.POST)
    public String guardarContratacion(@RequestParam("gestion") String gestion,
            @RequestParam("contratacion") Long contratacionId,
            Model model) {

        Contratacion contratacionSeleccionada = contratacionService.findOne(contratacionId);

        // Añadir los datos al modelo para mostrar en la nueva vista
        model.addAttribute("gestion", gestion);
        model.addAttribute("contratacionSeleccionada", contratacionSeleccionada);
        System.out.println("nooooooooooooo " + contratacionId);
        System.out.println("nooooooooooooo " + gestion);

        // Redirigir a la nueva vista
        return "redirect:/enviar-modificatorio?gestion=" + gestion + "&contratacionId=" + contratacionId;
    }

    @RequestMapping(value = "enviar-modificatorio", method = RequestMethod.GET)
    public String Contratacion(@RequestParam("gestion") String gestion,
            @RequestParam("contratacionId") Long contratacionId, @Validated Modificatorio modificatorio, Model model,
            HttpServletRequest request)
            throws Exception {

        if (request.getSession().getAttribute("usuario") != null) {

            Contratacion contratacionSeleccionada = contratacionService.findOne(contratacionId);
            String codigoContratacion = contratacionSeleccionada != null ? contratacionSeleccionada.getCodigo_contratacion() : null;
            String codigoContratacion1 = contratacionSeleccionada != null ? contratacionSeleccionada.getCargo_servicio_contratacion() : null;
            Double codigoContratacion2 = contratacionSeleccionada != null ? contratacionSeleccionada.getMonto_mensual_contratacion() : null;
            Double codigoContratacion3 = contratacionSeleccionada != null ? contratacionSeleccionada.getMonto_anual_contratacion() : null;
            Double codigoContratacion4 = contratacionSeleccionada != null ? contratacionSeleccionada.getMonto_anual_contratacion() : null;


            Modificatorio modificatorio2 = new Modificatorio();
            modificatorio2.setGestion_modificatorio(gestion);
            modificatorio2.setCodigo_modificatorio(codigoContratacion);
            modificatorio2.setCargo_servicio_modificatorio(codigoContratacion1);
            modificatorio2.setMonto_mensual_modificatorio(codigoContratacion2);
            modificatorio2.setMonto_anual_modificatorio(codigoContratacion3);
            model.addAttribute("modificatorio", modificatorio2);

            //model.addAttribute("contrataciones", contrataciones);
            //model.addAttribute("contratacion", new Contratacion());
            model.addAttribute("proyectos", proyectoService.findAll());
            model.addAttribute("tmodalidades", tipoModalidadService.findAll());
            model.addAttribute("personas", personaService.findAll());
            model.addAttribute("grados", gradoService.findAll());
            model.addAttribute("formularios", formularioService.findAll());

            //model.addAttribute("Rgestion", gestion);
            //model.addAttribute("Rmodalidad", nombreModalidad);
            model.addAttribute("Idcontratacion", contratacionSeleccionada);
            System.out.println("Gestión: " + gestion);
            System.out.println("ID de Modalidad: " + codigoContratacion);

            return "modificatorio/mostrar-modificatorio";
        } else {
            return "redirect:/";
        }
    }
}
