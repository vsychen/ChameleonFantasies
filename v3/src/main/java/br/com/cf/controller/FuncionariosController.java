package br.com.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.com.cf.domain.pojos.FuncionarioPOJO;
import br.com.cf.fachada.Fachada;

@Controller
@RequestMapping("/funcionario")
public class FuncionariosController {
	@Autowired
	private Fachada fachada;

	@RequestMapping(value = "/")
	public ModelAndView main() {
		return createView(fachada.procurarFuncionario(""));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/procurar")
	public ModelAndView procurar(@RequestParam String cpf) {
		return createView(fachada.procurarFuncionario(cpf));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public ModelAndView salvar(@ModelAttribute("funcionario") FuncionarioPOJO f) {
		fachada.salvarFuncionario(f);
		return createView(f);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/remover")
	public ModelAndView remover(@ModelAttribute("funcionario") FuncionarioPOJO f) {
		fachada.removerFuncionario(f.getCpf());
		return createView(fachada.procurarFuncionario(""));
	}

	private ModelAndView createView(FuncionarioPOJO object) {
		ModelAndView mav = new ModelAndView("funcionario.jsp");
		mav.addObject("funcionario", object);
		return mav;
	}
}