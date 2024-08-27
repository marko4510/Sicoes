package com.example.Proyecto.Controller.ReporteControllers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Proyecto.Models.Entity.Contratacion;
import com.example.Proyecto.Models.Entity.Persona;
import com.example.Proyecto.Models.IService.IContratacionService;
import com.example.Proyecto.Models.IService.IModalidadService;
import com.example.Proyecto.Models.IService.IPersonaService;
import com.example.Proyecto.Models.IService.IProyectoService;
import com.example.Proyecto.Models.IService.ITipoModalidadService;
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

    @Autowired
    private ITipoModalidadService tipoModalidadService;

    @RequestMapping(value = "ReporteCon", method = RequestMethod.GET)
    public String PlantillaBasia(HttpServletRequest request, Model model) {

        if (request.getSession().getAttribute("persona") != null) {
            List<Contratacion> contrataciones = contratacionService.findAll();
            Set<Integer> years = contrataciones.stream()
                    .map(doc -> doc.getFechaCreacion()
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .getYear())
                    .collect(Collectors.toSet());
            model.addAttribute("modalidales", modalidadService.findAll());
            model.addAttribute("proyectos", proyectoService.findAll());
            model.addAttribute("unidades", unidadService.findAll());
            model.addAttribute("tipoModalidades", tipoModalidadService.findAll());
            model.addAttribute("personas", personaService.findAll());
            model.addAttribute("years", years);

            return "reporte/generar-reporte";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/reporteGestion", method = RequestMethod.POST)
    public String Reporte_Gestion(@RequestParam("gestion1") String gestion, HttpServletRequest request,
            Model model) {

        if (request.getSession().getAttribute("persona") != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String fechaActual = sdf.format(new Date());
            model.addAttribute("fechaActual", fechaActual);
            model.addAttribute("contrataciones", contratacionService.findByGestionContratacion(gestion));
            model.addAttribute("gestion", gestion);

            return "reporte/unParametro/gestion";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reporteModalidad", method = RequestMethod.POST)
    public String Reporte_Modalidad(@RequestParam("nombre_modalidad") String nombre_modalidad,
            HttpServletRequest request, Model model) {

        if (request.getSession().getAttribute("persona") != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String fechaActual = sdf.format(new Date());
            model.addAttribute("fechaActual", fechaActual);
            model.addAttribute("contrataciones", contratacionService.findByNombreModalidad(nombre_modalidad));
            model.addAttribute("nombre_modalidad", nombre_modalidad);
            return "reporte/unParametro/modalidad";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reporteGestionUnidad", method = RequestMethod.POST)
    public String Reporte_Gestion_Unidad(@RequestParam("gestion2") String gestion,
            @RequestParam("nombre_unidad") String nombre_unidad, HttpServletRequest request, Model model) {

        if (request.getSession().getAttribute("persona") != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String fechaActual = sdf.format(new Date());
            model.addAttribute("fechaActual", fechaActual);
            model.addAttribute("contrataciones",
                    contratacionService.findByGestion_y_Unidad(gestion, nombre_unidad));
            model.addAttribute("nombre_unidad", nombre_unidad);
            model.addAttribute("gestion", gestion);

            return "reporte/dosParametro/gestion-unidad";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reporteGestionNumProyecto", method = RequestMethod.POST)
    public String Reporte_Gestion_NumProyecto(@RequestParam("gestion3") String gestion,
            @RequestParam("numero_proyecto") String numero_proyecto, HttpServletRequest request, Model model) {

        if (request.getSession().getAttribute("persona") != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String fechaActual = sdf.format(new Date());
            model.addAttribute("fechaActual", fechaActual);
            model.addAttribute("contrataciones",
                    contratacionService.findByGestion_y_NumProyecto(gestion, numero_proyecto));
            model.addAttribute("gestion", gestion);

            return "reporte/dosParametro/gestion-num-proyecto";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reporteGestionModalidad", method = RequestMethod.POST)
    public String Reporte_Gestion_Modalidad(@RequestParam("gestion4") String gestion,
            @RequestParam("nombre_modalidad2") String nombre_modalidad, HttpServletRequest request, Model model) {

        if (request.getSession().getAttribute("persona") != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String fechaActual = sdf.format(new Date());
            model.addAttribute("fechaActual", fechaActual);
            model.addAttribute("contrataciones",
                    contratacionService.findByGestion_y_Modalidad(gestion, nombre_modalidad));
            model.addAttribute("gestion", gestion);
            model.addAttribute("nombre_modalidad", nombre_modalidad);

            return "reporte/dosParametro/gestion-modalidad";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reporteGestionTipomodalidad", method = RequestMethod.POST)
    public String Reporte_Gestion_Tipomodalidad(@RequestParam("gestion5") String gestion,
            @RequestParam("nombre_tipo_modalidad") String nombre_tipo_modalidad, HttpServletRequest request,
            Model model) {

        if (request.getSession().getAttribute("persona") != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String fechaActual = sdf.format(new Date());
            model.addAttribute("fechaActual", fechaActual);
            model.addAttribute("contrataciones",
                    contratacionService.findByGestion_y_TipoModalidad(gestion, nombre_tipo_modalidad));
            model.addAttribute("gestion", gestion);
            model.addAttribute("nombre_tipo_modalidad", nombre_tipo_modalidad);

            return "reporte/dosParametro/gestion-tipo-modalidad";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reporteGestionAdjudicado", method = RequestMethod.POST)
    public String Reporte_Gestion_Adjudicado(@RequestParam("gestion6") String gestion,
            @RequestParam("id_persona") Long id_persona, HttpServletRequest request, Model model) {

        if (request.getSession().getAttribute("persona") != null) {
            Persona persona = personaService.findOne(id_persona);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String fechaActual = sdf.format(new Date());
            model.addAttribute("fechaActual", fechaActual);
            model.addAttribute("contrataciones",
                    contratacionService.findByGestion_y_Persona(gestion, id_persona));
            model.addAttribute("gestion", gestion);
            model.addAttribute("persona", persona);

            return "reporte/dosParametro/gestion-adjudicado";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reportGTU", method = RequestMethod.POST)
    public String Mostrar_Tabla_Reporte_Gestion_TipoModalidad_Unidad(@RequestParam("gestion7") String gestion,
            @RequestParam("nombre_tipo_modalidad2") String nombre_tipo_modalidad,
            @RequestParam("nombre_unidad2") String nombre_unidad, HttpServletRequest request, Model model) {

        if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("contrataciones",
                    contratacionService.findByGestion_TipoModalidad_Unidad(gestion, nombre_tipo_modalidad,
                            nombre_unidad));

            return "reporte/tabla-reporte";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reportGMT", method = RequestMethod.POST)
    public String Mostrar_Tabla_Reporte_Gestion_Modalidad_TipoModalidad(@RequestParam("gestion8") String gestion,
            @RequestParam("nombre_modalidad3") String nombre_modalidad,
            @RequestParam("nombre_tipo_modalidad3") String nombre_tipo_modalidad, HttpServletRequest request,
            Model model) {

        if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("contrataciones",
                    contratacionService.findByGestion_Modalidad_TipoModalidad(gestion, nombre_modalidad,
                            nombre_tipo_modalidad));

            return "reporte/tabla-reporte";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "reportGMN", method = RequestMethod.POST)
    public String Mostrar_Tabla_Reporte_Gestion_Modalidad_NumProyecto(@RequestParam("gestion9") String gestion,
            @RequestParam("nombre_modalidad4") String nombre_modalidad,
            @RequestParam("numero_proyecto2") String numero_proyecto, HttpServletRequest request, Model model) {

        if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("contrataciones",
                    contratacionService.findByGestion_Modalidad_NumProyecto(gestion, nombre_modalidad,
                            numero_proyecto));

            return "reporte/tabla-reporte";
        } else {
            return "redirect:/";
        }
    }

}
