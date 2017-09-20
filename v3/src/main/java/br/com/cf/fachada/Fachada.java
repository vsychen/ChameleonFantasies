package br.com.cf.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cf.domain.pojos.ClientePOJO;
import br.com.cf.domain.pojos.FantasiaPOJO;
import br.com.cf.domain.pojos.FuncionarioPOJO;
import br.com.cf.service.CustomService;

@Service("Fachada")
public class Fachada {
	@Autowired
	CustomService<ClientePOJO> clientes;

	@Autowired
	CustomService<FantasiaPOJO> fantasias;

	@Autowired
	CustomService<FuncionarioPOJO> funcionarios;

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
		fantasias.salvar(f);
	}

	public FantasiaPOJO procurarFantasia(String codigo) {
		return fantasias.procurar(codigo);
	}

	public void editarFantasia(FantasiaPOJO f) {
		fantasias.atualizar(f);
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