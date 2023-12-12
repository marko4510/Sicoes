package com.example.Proyecto.Controller.PersonaControllers;

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

import com.example.Proyecto.Models.Entity.Persona;
import com.example.Proyecto.Models.IService.IPersonaService;

@Controller
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @RequestMapping(value = "PersonaR", method = RequestMethod.GET)
    public String PersonaR(@Validated Persona persona, Model model, HttpServletRequest request) throws Exception {

        if (request.getSession().getAttribute("persona") != null) {
            List<Persona> personas = personaService.findAll();

            model.addAttribute("personas", personas);

            return "persona/persona-adm";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "PersonaF", method = RequestMethod.POST)
    public String PersonaF(HttpServletRequest request, @Validated Persona persona) {

        persona.setEstado_persona("A");
        personaService.save(persona);

        return "redirect:/PersonaR";
    }

    @RequestMapping(value = "/editar-persona/{id_persona}")
    public String editar_c(@PathVariable("id_persona") String id_persona, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("persona") != null) {
            try {
                Long id_per = Long.parseLong(id_persona);
                Persona persona = personaService.findOne(id_per);
                model.addAttribute("persona", persona);

                List<Persona> personas = personaService.findAll();

                model.addAttribute("personas", personas);

                return "persona/persona-adm";

            } catch (Exception e) {

                return "redirect:/AdmPG";
            }
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/PersonaModF", method = RequestMethod.POST) // Enviar datos de Registro a Lista
    public String tpconvenio_mod(HttpServletRequest request, @Validated Persona persona,
            RedirectAttributes redirectAttrs) { // validar los datos capturados (1)

        // Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        persona.setEstado_persona("A");
        personaService.save(persona);
        return "redirect:/PersonaR";
    }

    @RequestMapping(value = "/eliminar-persona/{id_persona}")
    public String eliminar_c(HttpServletRequest request, @PathVariable("id_persona") String id_persona)
            throws Exception {
        if (request.getSession().getAttribute("usuario") != null) {
            try {
                Long id_per = Long.parseLong(id_persona);
                Persona persona = personaService.findOne(id_per);
                persona.setEstado_persona("X");
                personaService.save(persona);
                return "redirect:/PersonaR";
            } catch (Exception e) {
                return "redirect:/AdmPG";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/tablePersonas")
    public String tablePersonas(@Validated Persona persona, Model model) throws Exception {

        List<Persona> personas = personaService.findAll();

        model.addAttribute("personas", personas);

        return "persona/tableFragmentPer :: table";
    }

}
