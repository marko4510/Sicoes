package com.example.Proyecto.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    
     @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {

        return "login/login-main";
    }


    // Funcion de visualizaciòn de la pagina principal
	@RequestMapping(value = "/AdmPG", method = RequestMethod.GET) // Pagina principal
	public String Inicio(HttpServletRequest request, Model model) {
		if (request.getSession().getAttribute("usuario") != null) {
		
		
			
			return "/adm/inicio-adm";
		} else {
			return "redirect:/";
		}
	}
}
