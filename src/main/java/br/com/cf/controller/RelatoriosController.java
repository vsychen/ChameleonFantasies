package br.com.cf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cf.fachada.Fachada;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	@Autowired
	Fachada fachada;

	@RequestMapping("/")
	public ModelAndView main() {
		return createView(null);
	}

	@RequestMapping("/cliente")
	public ModelAndView relatorioClientes() {
		return createView(fachada.listarClientes());
	}

	@RequestMapping("/funcionario")
	public ModelAndView relatorioFuncionarios() {
		return createView(fachada.listarFuncionarios());
	}

	@RequestMapping("/fantasia")
	public ModelAndView relatorioFantasia() {
		return createView(fachada.listarFantasias());
	}

	private ModelAndView createView(List<?> objects) {
		ModelAndView mav = new ModelAndView("relatorios.jsp");
		mav.addObject("items", objects);
		return mav;
	}
}