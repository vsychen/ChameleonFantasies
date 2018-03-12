package dados.repositorios.array;

import java.io.Serializable;

import dados.base.pessoas.Cliente;
import dados.base.excecoes.*;
import dados.repositorios.interfaces.IRepositorioCliente;
import dados.repositorios.iterator.Iterator;

public class RepositorioArrayCliente implements IRepositorioCliente, Serializable {
	private static final long serialVersionUID = -6219810224499353766L;
	private Cliente[] clientes;

	public RepositorioArrayCliente() {
		clientes = new Cliente[1];
	}

	public void inserir(Cliente cliente) throws PessoaCadastradaException {
		int indice = getIndice();

		for (int i = 0; i <= indice; i++) {
			if (clientes[i] != null && cliente.getCpf().equals(clientes[i].getCpf())) {
				throw new PessoaCadastradaException();
			}
		}

		if (indice != clientes.length - 1) {
			clientes[indice + 1] = cliente;
		} else {
			Cliente[] temp = new Cliente[clientes.length + 1];

			for (int i = 0; i < clientes.length; i++) {
				temp[i] = clientes[i];
			}

			clientes = temp;
			clientes[clientes.length - 1] = cliente;
		}
	}

	public Cliente procurar(String cpf) throws PessoaInexistenteException {
		Cliente cliente = null;
		int indice = getIndice();

		for (int i = 0; i <= indice; i++) {
			if (clientes[i] != null && clientes[i].getCpf().equals(cpf))
				cliente = clientes[i];
		}

		if (cliente == null) {
			throw new PessoaInexistenteException();
		}

		return cliente;
	}

	public void remover(String cpf) throws PessoaInexistenteException {
		int indice = getIndice();
		boolean existe = false;

		for (int i = 0; i <= indice; i++) {
			if (clientes[i] != null && clientes[i].getCpf().equals(cpf)) {
				clientes[i] = null;
				existe = true;
			}
		}

		if (!existe) {
			throw new PessoaInexistenteException();
		}

		Cliente[] temp = new Cliente[clientes.length - 1];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < clientes.length; j++) {
				if (temp[i] == null && clientes[j] != null) {
					temp[i] = clientes[j];
					clientes[j] = null;
					j = clientes.length;
				}
			}
		}

		clientes = temp;
	}

	public void atualizar(Cliente cliente) throws PessoaInexistenteException {
		int indice = getIndice();
		boolean existe = false;

		for (int i = 0; i <= indice; i++) {
			if (clientes[i] != null && clientes[i].getCpf().equals(cliente.getCpf())) {
				clientes[i] = cliente;
				existe = true;
				i = indice;
			}
		}

		if (!existe) {
			throw new PessoaInexistenteException();
		}
	}

	public int getIndice() {
		int indice = -1;

		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] != null)
				indice = i;
		}

		return indice;
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