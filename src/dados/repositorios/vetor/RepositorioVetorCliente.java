package dados.repositorios.vetor;

import java.io.Serializable;
import java.util.Vector;

import dados.repositorios.interfaces.IRepositorioCliente;
import dados.repositorios.iterator.Iterator;
import dados.base.pessoas.Cliente;
import dados.base.excecoes.*;

public class RepositorioVetorCliente implements IRepositorioCliente, Serializable {
	private static final long serialVersionUID = 173626136115780028L;
	private Vector<Cliente> clientes;

	public RepositorioVetorCliente() {
		clientes = new Vector<Cliente>();
	}

	public void inserir(Cliente cliente) throws PessoaCadastradaException {
		Cliente temp;

		for (int i = 0; i < clientes.size(); i++) {
			temp = clientes.get(i);

			if (temp.getCpf().equals(cliente.getCpf())) {
				throw new PessoaCadastradaException();
			}
		}

		clientes.add(cliente);
	}

	public Cliente procurar(String cpf) throws PessoaInexistenteException {
		Cliente cliente = null;
		boolean achou = false;

		for (int i = 0; i < clientes.size(); i++) {
			cliente = clientes.get(i);

			if (cliente.getCpf().equals(cpf)) {
				i = clientes.size();
				achou = true;
			}
		}

		if (!achou) {
			throw new PessoaInexistenteException();
		}

		return cliente;
	}

	public void remover(String cpf) throws PessoaInexistenteException {
		boolean achou = false;

		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = clientes.get(i);

			if (cliente.getCpf().equals(cpf)) {
				clientes.remove(i);
				achou = true;
			}
		}

		if (!achou) {
			throw new PessoaInexistenteException();
		}
	}

	public void atualizar(Cliente cliente) throws PessoaInexistenteException {
		Cliente temp = null;
		boolean achou = false;

		for (int i = 0; i < clientes.size(); i++) {
			temp = clientes.get(i);

			if (temp.getCpf().equals(cliente.getCpf())) {
				clientes.set(i, cliente);
				achou = true;
			}
		}

		if (!achou) {
			throw new PessoaInexistenteException();
		}
	}

	public String gerarRelatorio() {
		Iterator<Cliente> iterator = getIterator();
		String retorno = "";

		while (iterator.hasNext()) {
			retorno = retorno + iterator.next();
		}

		return retorno;
	}

	public Iterator<Cliente> getIterator() {
		Iterator<Cliente> iterator = new Iterator<Cliente>(clientes);
		return iterator;
	}
}