package br.com.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.cf.domain.pojos.FantasiaPOJO;
import br.com.cf.fachada.Fachada;

@Controller
@RequestMapping("/fantasia")
public class FantasiaController {
	@Autowired
	private Fachada fachada;

	@RequestMapping(value = "/")
	public ModelAndView main() {
		return createView(fachada.procurarFantasia(""));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/procurar")
	public ModelAndView procurar(@RequestParam String codigo) {
		return createView(fachada.procurarFantasia(codigo));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public ModelAndView salvar(@ModelAttribute FantasiaPOJO f) {
		fachada.salvarFantasia(f);
		return createView(f);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/remover")
	public ModelAndView remover(@ModelAttribute("fantasia") FantasiaPOJO f) {
		fachada.removerFantasia(f.getCodigo());
		return createView(fachada.procurarFantasia(""));
	}

	private ModelAndView createView(FantasiaPOJO object) {
		ModelAndView mav = new ModelAndView("fantasia.jsp");
		mav.addObject("fantasia", object);
		return mav;
	}
}