package br.com.cf.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cf.domain.pojos.ClientePOJO;
import br.com.cf.domain.pojos.FantasiaPOJO;
import br.com.cf.domain.pojos.FuncionarioPOJO;
import br.com.cf.service.ClienteService;
import br.com.cf.service.FantasiaService;
import br.com.cf.service.FuncionarioService;

@Service("Fachada")
public class Fachada {
	@Autowired
	ClienteService clientes;

	@Autowired
	FantasiaService fantasias;

	@Autowired
	FuncionarioService funcionarios;

	private Fachada() {

	}

	// ClienteServiceImpl
	public void salvarCliente(ClientePOJO c) {
		clientes.salvarOuAtualizar(c);
	}

	public ClientePOJO procurarCliente(String cpf) {
		return clientes.procurar(cpf);
	}

	public void editarCliente(ClientePOJO c) {
		clientes.atualizar(c);
	}

	public void removerCliente(String cpf) {
		clientes.remover(cpf);
	}

	public List<ClientePOJO> listarClientes() {
		return clientes.listar();
	}

	// FantasiaServiceImpl
	public void salvarFantasia(FantasiaPOJO f) {
		fantasias.salvarOuAtualizar(f);
	}

	public FantasiaPOJO procurarFantasia(String codigo) {
		return fantasias.procurar(codigo);
	}

	public void editarFantasia(FantasiaPOJO f) {
		fantasias.atualizar(f);
	}

	public void comprarFantasias(String codigo, int quantidade) {
		fantasias.comprarFantasias(codigo, quantidade);
	}

	public void venderFantasias(String codigo, String cpf, int quantidade) {
		if (cpf.equals(""))
			fantasias.venderFantasias(codigo, quantidade);
		else
			fantasias.venderFantasias(codigo, cpf, quantidade);
	}

	public void mudarPrecos(String codigo, String precoCompra, String precoVenda) {
		fantasias.mudarPrecos(codigo, precoCompra, precoVenda);
	}

	public void removerFantasia(String codigo) {
		fantasias.remover(codigo);
	}

	public List<FantasiaPOJO> listarFantasias() {
		return fantasias.listar();
	}

	// FuncionarioServiceImpl
	public void salvarFuncionario(FuncionarioPOJO f) {
		funcionarios.salvarOuAtualizar(f);
	}

	public FuncionarioPOJO procurarFuncionario(String cpf) {
		return funcionarios.procurar(cpf);
	}

	public void editarFuncionario(FuncionarioPOJO f) {
		funcionarios.atualizar(f);
	}

	public void removerFuncionario(String cpf) {
		funcionarios.remover(cpf);
	}

	public List<FuncionarioPOJO> listarFuncionarios() {
		return funcionarios.listar();
	}
}