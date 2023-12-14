package com.example.Proyecto.Controller.ModificatorioControllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Proyecto.Models.Entity.ArchivoAdjunto;
import com.example.Proyecto.Models.Entity.Contratacion;
import com.example.Proyecto.Models.Entity.Grado;
import com.example.Proyecto.Models.Entity.Modalidad;
import com.example.Proyecto.Models.Entity.Modificatorio;
import com.example.Proyecto.Models.Entity.Persona;
import com.example.Proyecto.Models.Entity.Proyecto;
import com.example.Proyecto.Models.Entity.TipoModalidad;
import com.example.Proyecto.Models.Entity.Unidad;
import com.example.Proyecto.Models.Entity.Usuario;
import com.example.Proyecto.Models.IService.IArchivoAdjuntoService;
import com.example.Proyecto.Models.IService.IContratacionService;
import com.example.Proyecto.Models.IService.IGradoService;
import com.example.Proyecto.Models.IService.IModalidadService;
import com.example.Proyecto.Models.IService.IModificatorioService;
import com.example.Proyecto.Models.IService.IPersonaService;
import com.example.Proyecto.Models.IService.IProyectoService;
import com.example.Proyecto.Models.IService.ITipoModalidadService;
import com.example.Proyecto.Models.Otros.AdjuntarArchivo;
import com.example.Proyecto.Models.Otros.AlfaNum;

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
        private IArchivoAdjuntoService archivoAdjuntoService;

        @Autowired
        private IModalidadService modalidadService;

        @RequestMapping(value = "ModificatorioInicio", method = RequestMethod.GET)
        public String ModificatorioInicio(HttpServletRequest request, @Validated Modificatorio modificatorio,
                        Model model)
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
                        @RequestParam("contratacionId") Long contratacionId, @Validated Modificatorio modificatorio,
                        Model model,
                        HttpServletRequest request)
                        throws Exception {

                if (request.getSession().getAttribute("usuario") != null) {

                        Contratacion contratacionSeleccionada = contratacionService.findOne(contratacionId);

                        String codigoContratacion = contratacionSeleccionada != null
                                        ? contratacionSeleccionada.getCodigo_contratacion()
                                        : null;
                        String codigoContratacion1 = contratacionSeleccionada != null
                                        ? contratacionSeleccionada.getCargo_servicio_contratacion()
                                        : null;
                        Double codigoContratacion2 = contratacionSeleccionada != null
                                        ? contratacionSeleccionada.getMonto_mensual_contratacion()
                                        : null;
                        Double codigoContratacion3 = contratacionSeleccionada != null
                                        ? contratacionSeleccionada.getMonto_anual_contratacion()
                                        : null;
                        String codigoContratacion10 = contratacionSeleccionada != null
                                        ? contratacionSeleccionada.getPartida_contratacion()
                                        : null;
                        String codigoContratacion4 = contratacionSeleccionada != null
                                        ? contratacionSeleccionada.getDias_calendario_contratacion()
                                        : null;
                        Long codigoContratacion5 = contratacionSeleccionada != null
                                        ? contratacionSeleccionada.getModalidad().getId_modalidad()
                                        : null;

                        Modalidad modalidadSeleccionada = modalidadService.findOne(codigoContratacion5);

                        Timestamp fechaInicioContratacionTimestamp = (Timestamp) contratacionSeleccionada
                                        .getFecha_inicio_contratacion();

                        Timestamp fechaFinContratacionTimestamp = (Timestamp) contratacionSeleccionada
                                        .getFecha_fin_contratacion();

                        Long codigoContratacion6 = contratacionSeleccionada != null
                                        ? contratacionSeleccionada.getTipoModalidad().getId_tipo_modalidad()
                                        : null;

                        TipoModalidad tipoModalidadSelec = tipoModalidadService.findOne(codigoContratacion6);

                        Long codigoContratacion7 = contratacionSeleccionada != null
                                        ? contratacionSeleccionada.getPersona().getId_persona()
                                        : null;

                        Persona personaSelec = personaService.findOne(codigoContratacion7);

                        Long codigoContratacion8 = contratacionSeleccionada != null
                                        ? contratacionSeleccionada.getGrado().getId_grado()
                                        : null;

                        Grado gradoSelec = gradoService.findOne(codigoContratacion8);

                        Long codigoContratacion9 = contratacionSeleccionada != null
                                        ? contratacionSeleccionada.getProyecto().getId_proyecto()
                                        : null;

                        Proyecto proyectoSelec = proyectoService.findOne(codigoContratacion9);

                        Long cantidadM = modificatorioService.cantidadModificatorio(contratacionId);
                        String cantidadComoString = (cantidadM != null) ? String.valueOf(cantidadM + 1) : null;

                        System.out.println("vamos a ver " + cantidadComoString);

                        Modificatorio modificatorio2 = new Modificatorio();
                        modificatorio2.setGestion_modificatorio(gestion);
                        modificatorio2.setCodigo_modificatorio(codigoContratacion);
                        modificatorio2.setCargo_servicio_modificatorio(codigoContratacion1);
                        modificatorio2.setMonto_mensual_modificatorio(codigoContratacion2);
                        modificatorio2.setMonto_anual_modificatorio(codigoContratacion3);
                        modificatorio2.setPartida_modificatorio(codigoContratacion10);
                        modificatorio2.setFecha_inicio_modificatorio(fechaInicioContratacionTimestamp);
                        modificatorio2.setFecha_fin_modificatorio(fechaFinContratacionTimestamp);
                        modificatorio2.setDias_calendario_modificatorio(codigoContratacion4);
                        modificatorio2.setModalidad(modalidadSeleccionada);
                        modificatorio2.setTipoModalidad(tipoModalidadSelec);
                        modificatorio2.setPersona(personaSelec);
                        modificatorio2.setGrado(gradoSelec);
                        modificatorio2.setContratacion(contratacionSeleccionada);
                        modificatorio2.setProyecto(proyectoSelec);
                        modificatorio2.setNro_modificacion_contratacion(cantidadComoString);
                        model.addAttribute("modificatorio", modificatorio2);
                        model.addAttribute("Idmodalidad", modalidadService.findAll());
                        model.addAttribute("IdTipomodalidad", tipoModalidadService.findAll());
                        model.addAttribute("Idpersona", personaService.findAll());
                        model.addAttribute("Idgrado", gradoService.findAll());
                        model.addAttribute("Idproyecto", proyectoService.findAll());

                        // model.addAttribute("cantmodifi",
                        // modificatorioService.cantidadModificatorio(contratacionId));

                        System.out.println("Gestión: " + gestion);
                        System.out.println("ID de Modalidad: " + codigoContratacion);

                        return "modificatorio/mostrar-modificatorio";
                } else {
                        return "redirect:/";
                }
        }

        @RequestMapping(value = "ModificatorioF", method = RequestMethod.POST)
        public String ModificatorioF(HttpServletRequest request, @Validated Modificatorio modificatorio)
                        throws FileNotFoundException, IOException {

                MultipartFile multipartFile = modificatorio.getFile();
                ArchivoAdjunto archivoAdjunto = new ArchivoAdjunto();
                List<ArchivoAdjunto> listArchivos = archivoAdjuntoService.listarArchivoAdjunto();
                AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();

                if (multipartFile != null && !multipartFile.isEmpty()) {
                        try {
                                Path rootPath = Paths.get("archivos/");
                                Path rootAbsolutePath = rootPath.toAbsolutePath();
                                String rutaDirectorio = rootAbsolutePath.toString() + "/";

                                if (!Files.exists(rootPath)) {
                                        Files.createDirectories(rootPath);
                                        System.out.println("Directorio creado: " + rutaDirectorio);
                                } else {
                                        System.out.println("El directorio ya existe: " + rutaDirectorio);
                                }

                                String nombreArchivo = (listArchivos.size() + 1)
                                                + modificatorio.getModalidad().getNombre_modalidad()
                                                + "-" + modificatorio.getGestion_modificatorio() + ".pdf";

                                archivoAdjunto.setNombre_archivo(nombreArchivo);
                                archivoAdjunto.setRuta_archivo_adjunto(rutaDirectorio);
                                archivoAdjunto.setEstado_archivo_adjunto("A");
                                ArchivoAdjunto archivoAdjunto2 = archivoAdjuntoService
                                                .registrarArchivoAdjunto(archivoAdjunto);

                                modificatorio.setCodigo_modificatorio(
                                                modificatorio.getModalidad().getNombre_modalidad() + "-"
                                                                + modificatorio.getGestion_modificatorio());
                                modificatorio.setArchivoAdjunto(archivoAdjunto2);
                                modificatorio.setNombreArchivo(nombreArchivo);
                                modificatorio.setEstado_modificatorio("A");
                                modificatorioService.save(modificatorio);
                                Integer ad = adjuntarArchivo.adjuntarArchivoModificatorio(modificatorio,
                                                rutaDirectorio);

                        } catch (IOException e) {
                                System.err.println("Error al crear el directorio: " + e.getMessage());
                        }
                } else {
                        System.out.println("no llegue a registrarme pipipipipi");
                }

                return "redirect:/ModificatorioL";
        }

        @RequestMapping(value = "ModificatorioL", method = RequestMethod.GET)
        public String modificatorioL(@Validated Modificatorio modificatorio, Model model, HttpServletRequest request)
                        throws Exception {

                if (request.getSession().getAttribute("persona") != null) {
                        List<Modificatorio> modificatorios = modificatorioService.findAll();

                        model.addAttribute("modificatorios", modificatorios);

                        return "modificatorio/listar-modificatorio";
                } else {
                        return "redirect:/";
                }
        }

        @RequestMapping(value = "/openFileModificatorio/{id}", method = RequestMethod.GET, produces = "application/pdf")
        public @ResponseBody FileSystemResource abrirArchivoMedianteResourse(HttpServletResponse response,
                        @PathVariable("id") long id_modificatorio) throws FileNotFoundException {

                ArchivoAdjunto ArchivoAdjuntos = archivoAdjuntoService
                                .buscarArchivoAdjuntoPorModificatorio(id_modificatorio);
                File file = new File(ArchivoAdjuntos.getRuta_archivo_adjunto() + ArchivoAdjuntos.getNombre_archivo());
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
                response.setHeader("Content-Length", String.valueOf(file.length()));
                return new FileSystemResource(file);
        }

        @RequestMapping(value = "/editar-modificatorio/{id_modificatorio}")
        public String editar_modificatorio(@PathVariable("id_modificatorio") String id_modificatorio, Model model,
                        HttpServletRequest request)
                        throws NumberFormatException, Exception {
                if (request.getSession().getAttribute("usuario") != null) {

                        Long id_mod = Long.parseLong(id_modificatorio);
                        Modificatorio modificatorio = modificatorioService.findOne(id_mod);

                        // List<TipoModalidad> tipoModalidades = tipoModalidadService.findAll();
                        // model.addAttribute("IdTipomodalidad", tipoModalidades);

                        model.addAttribute("Idmodalidad", modalidadService.findAll());
                        model.addAttribute("IdTipomodalidad", tipoModalidadService.findAll());

                        model.addAttribute("modificatorio", modificatorio);

                        return "modificatorio/mostrar-modificatorio";
                } else {
                        return "redirect:/";
                }

        }

        

        @PostMapping(value = "/ModificatorioModF")
        public String ModificatorioModF(@Validated Modificatorio modificatorio, RedirectAttributes redirectAttrs,
                        Model model,
                        HttpServletRequest request)
                        throws IOException {
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

                MultipartFile multipartFile = modificatorio.getFile();
                ArchivoAdjunto archivoAdjunto = new ArchivoAdjunto();
                AdjuntarArchivo adjuntarArchivo = new AdjuntarArchivo();
                AlfaNum alfaNum = new AlfaNum();
                String nombre_r = alfaNum.generarAlfanumerico();

                Path rootPath = Paths.get("archivos/");
                Path rootAbsolutePath = rootPath.toAbsolutePath();
                String rutaDirectorio = rootAbsolutePath.toString() + "/";
                modificatorio.setNombreArchivo(nombre_r + ".pdf");
                Integer ad = adjuntarArchivo.adjuntarArchivoModificatorio(modificatorio, rutaDirectorio);
                if (ad == 1) {
                        ArchivoAdjunto barchivoAdjunto = archivoAdjuntoService.buscarArchivoAdjuntoPorModificatorio(modificatorio.getId_modificatorio());
                       

                        barchivoAdjunto.setNombre_archivo(modificatorio.getNombreArchivo());
                        barchivoAdjunto.setRuta_archivo_adjunto(rutaDirectorio);
                        archivoAdjuntoService.modificarArchivoAdjunto(barchivoAdjunto);
                }
               
                modificatorio.setEstado_modificatorio("A");
                modificatorioService.save(modificatorio);

                return "redirect:/ContratacionL";

        }
        
}
