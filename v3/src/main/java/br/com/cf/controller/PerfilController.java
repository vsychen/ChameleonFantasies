package br.com.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cf.fachada.Fachada;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	@Autowired
	Fachada fachada;

	private String example = "111.111.111-11"; // TODO authentication

	@RequestMapping("/")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView("perfil.jsp");
		mav.addObject("perfil", fachada.procurarFuncionario(example));
		return mav;
	}
}