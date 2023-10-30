package com.example.Proyecto.Controller.ContratacionControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.Proyecto.Models.Entity.ArchivoAdjunto;
import com.example.Proyecto.Models.Entity.Contratacion;
import com.example.Proyecto.Models.IService.IArchivoAdjuntoService;
import com.example.Proyecto.Models.IService.IContratacionService;
import com.example.Proyecto.Models.IService.IFormularioService;
import com.example.Proyecto.Models.IService.IGradoService;
import com.example.Proyecto.Models.IService.IModalidadService;
import com.example.Proyecto.Models.IService.IPersonaService;
import com.example.Proyecto.Models.IService.IProyectoService;
import com.example.Proyecto.Models.IService.ITipoModalidadService;
import com.example.Proyecto.Models.Otros.AdjuntarArchivo;
import com.example.Proyecto.Models.Otros.Encryptar;

@Controller
public class ContratacionController {

    @Autowired
    private IContratacionService contratacionService;

    @Autowired
    private IProyectoService proyectoService;

    @Autowired
    private IModalidadService modalidadService;

    @Autowired
    private ITipoModalidadService tipoModalidadService;

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IGradoService gradoService;

    @Autowired
    private IFormularioService formularioService;

     @Autowired
    private IArchivoAdjuntoService archivoAdjuntoService;

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

     @RequestMapping(value = "ContratacionForm", method = RequestMethod.GET)
    public String ContratacionForm(HttpServletRequest request, @Validated Contratacion contratacion, Model model)
            throws Exception {
        if (request.getSession().getAttribute("usuario") != null) {
      
            List<Contratacion> contrataciones = contratacionService.findAll();

            model.addAttribute("contrataciones", contrataciones);
            model.addAttribute("contratacion", new Contratacion());
            model.addAttribute("proyectos", proyectoService.findAll());
            model.addAttribute("modalidades", modalidadService.findAll());
            model.addAttribute("tmodalidades", tipoModalidadService.findAll());
            model.addAttribute("personas", personaService.findAll());
            model.addAttribute("grados", gradoService.findAll());
            model.addAttribute("formularios", formularioService.findAll());
        
            return "contratacion/contratacion-form";

        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "ContratacionF", method = RequestMethod.POST)
    public String ContratacionF(HttpServletRequest request, @Validated Contratacion contratacion,
     @RequestParam(value = "formulario") Long[] id_formularios) throws FileNotFoundException, IOException { 
        
         MultipartFile multipartFile = contratacion.getFile();
        ArchivoAdjunto archivoAdjunto = new ArchivoAdjunto();
         List<ArchivoAdjunto> listArchivos = archivoAdjuntoService.listarArchivoAdjunto();
         AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();

      if (multipartFile != null && !multipartFile.isEmpty()) {
        try {
            Path rootPath = Paths.get("archivos/");
            Path rootAbsolutePath = rootPath.toAbsolutePath();
            String rutaDirectorio = rootAbsolutePath.toString() +"/";

            if (!Files.exists(rootPath)) {
                Files.createDirectories(rootPath);
                System.out.println("Directorio creado: " + rutaDirectorio);
            } else {
                System.out.println("El directorio ya existe: " + rutaDirectorio);
            }

            String nombreArchivo = (listArchivos.size() + 1) + contratacion.getModalidad().getNombre_modalidad() + "-" + contratacion.getGestion_contratacion() + ".pdf";

            archivoAdjunto.setNombre_archivo(nombreArchivo);
            archivoAdjunto.setRuta_archivo_adjunto(rutaDirectorio);
            archivoAdjunto.setEstado_archivo_adjunto("A");
            ArchivoAdjunto archivoAdjunto2 = archivoAdjuntoService.registrarArchivoAdjunto(archivoAdjunto);

            contratacion.setCodigo_contratacion(contratacion.getModalidad().getNombre_modalidad() + "-" + contratacion.getGestion_contratacion());
            contratacion.setArchivoAdjunto(archivoAdjunto2);
            contratacion.setNombreArchivo(nombreArchivo);
            contratacion.setEstado_contratacion("A");
            contratacionService.save(contratacion);
            Integer ad = adjuntarArchivo.adjuntarArchivoContratacion(contratacion, rutaDirectorio);


        } catch (IOException e) {
            System.err.println("Error al crear el directorio: " + e.getMessage());
        }
    } else {
        // Handle the case where no file is uploaded
    }
        
        


        return "redirect:/ContratacionR";
    }


     @RequestMapping(value = "/editar-contratacion/{id_contratacion}")
    public String editar_contratacion(@PathVariable("id_contratacion") String id_contratacion, Model model, HttpServletRequest request)
            throws NumberFormatException, Exception {
                  if (request.getSession().getAttribute("usuario") != null) {

                    Long id_contra = Long.parseLong(id_contratacion);

          
            model.addAttribute("contratacion", contratacionService.findOne(id_contra));
            model.addAttribute("proyectos", proyectoService.findAll());
            model.addAttribute("modalidades", modalidadService.findAll());
            model.addAttribute("tmodalidades", tipoModalidadService.findAll());
            model.addAttribute("personas", personaService.findAll());
            model.addAttribute("grados", gradoService.findAll());
            model.addAttribute("formularios", formularioService.findAll());
            model.addAttribute("edit", "true");

            return "contratacion/contratacion-form";
                }else{
             return "redirect:/adm/InicioAdm";
                }
     

          
       
          
        

    }

    @RequestMapping(value = "/openFileContrato/{id}", method = RequestMethod.GET, produces = "application/pdf")
    public @ResponseBody FileSystemResource abrirArchivoMedianteResourse(HttpServletResponse response,
            @PathVariable("id") long id_contratacion) throws FileNotFoundException {
        ArchivoAdjunto ArchivoAdjuntos = archivoAdjuntoService.buscarArchivoAdjuntoPorContratacion(id_contratacion);
        File file = new File(ArchivoAdjuntos.getRuta_archivo_adjunto() + ArchivoAdjuntos.getNombre_archivo());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        return new FileSystemResource(file);
    }



}
