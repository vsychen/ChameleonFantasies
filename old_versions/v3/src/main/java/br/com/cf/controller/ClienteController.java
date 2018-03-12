package br.com.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.cf.domain.pojos.ClientePOJO;
import br.com.cf.fachada.Fachada;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private Fachada fachada;

	@RequestMapping(value = "/")
	public ModelAndView main() {
		return createView(fachada.procurarCliente(""));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/procurar")
	public ModelAndView procurar(@RequestParam String cpf) {
		return createView(fachada.procurarCliente(cpf));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public ModelAndView salvar(@ModelAttribute("cliente") ClientePOJO c) {
		fachada.salvarCliente(c);
		return createView(c);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/remover")
	public ModelAndView remover(@ModelAttribute("cliente") ClientePOJO c) {
		fachada.removerCliente(c.getCpf());
		return createView(fachada.procurarCliente(""));
	}

	private ModelAndView createView(ClientePOJO object) {
		ModelAndView mav = new ModelAndView("cliente.jsp");
		mav.addObject("cliente", object);
		return mav;
	}
}