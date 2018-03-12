package dados.repositorios.array;

import java.io.Serializable;

import dados.base.excecoes.PessoaCadastradaException;
import dados.base.excecoes.PessoaInexistenteException;
import dados.base.pessoas.Funcionario;
import dados.repositorios.interfaces.IRepositorioFuncionario;
import dados.repositorios.iterator.Iterator;

public class RepositorioArrayFuncionario implements IRepositorioFuncionario, Serializable {
	private static final long serialVersionUID = -5682347532608791804L;
	private Funcionario[] funcionarios;

	public RepositorioArrayFuncionario() {
		funcionarios = new Funcionario[1];
	}

	public void inserir(Funcionario funcionario) throws PessoaCadastradaException {
		int indice = getIndice();

		for (int i = 0; i <= indice; i++) {
			if (funcionarios[i] != null && funcionario.getCpf().equals(funcionarios[i].getCpf())) {
				throw new PessoaCadastradaException();
			}
		}

		if (indice != funcionarios.length - 1) {
			funcionarios[indice + 1] = funcionario;
		} else {
			Funcionario[] temp = new Funcionario[funcionarios.length + 1];

			for (int i = 0; i < funcionarios.length; i++) {
				temp[i] = funcionarios[i];
			}

			funcionarios = temp;
			funcionarios[funcionarios.length - 1] = funcionario;
		}
	}

	public Funcionario procurar(String cpf) throws PessoaInexistenteException {
		Funcionario funcionario = null;
		int indice = getIndice();

		for (int i = 0; i <= indice; i++) {
			if (funcionarios[i] != null && funcionarios[i].getCpf().equals(cpf)) {
				funcionario = funcionarios[i];
			}
		}

		if (funcionario == null) {
			throw new PessoaInexistenteException();
		}

		return funcionario;
	}

	public void remover(String cpf) throws PessoaInexistenteException {
		int indice = getIndice();
		boolean existe = false;

		for (int i = 0; i <= indice; i++) {
			if (funcionarios[i] != null && funcionarios[i].getCpf().equals(cpf)) {
				funcionarios[i] = null;
				existe = true;
			}
		}

		if (!existe) {
			throw new PessoaInexistenteException();
		}

		Funcionario[] temp = new Funcionario[funcionarios.length - 1];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < funcionarios.length; j++) {
				if (temp[i] == null && funcionarios[j] != null) {
					temp[i] = funcionarios[j];
					funcionarios[j] = null;
					j = funcionarios.length;
				}
			}
		}

		funcionarios = temp;
	}

	public void atualizar(Funcionario funcionario) throws PessoaInexistenteException {
		int indice = getIndice();
		boolean existe = false;

		for (int i = 0; i <= indice; i++) {
			if (funcionarios[i] != null && funcionarios[i].getCpf().equals(funcionario.getCpf())) {
				funcionarios[i] = funcionario;
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

		for (int i = 0; i < funcionarios.length; i++) {
			if (funcionarios[i] != null) {
				indice = i;
			}
		}

		return indice;
	}

	public String gerarRelatorio() {
		Iterator<Funcionario> iterator = getIterator();
		String retorno = "";

		while (iterator.hasNext()) {
			retorno = retorno + iterator.next();
		}

		return retorno;
	}

	public Iterator<Funcionario> getIterator() {
		Iterator<Funcionario> iterator = new Iterator<Funcionario>(funcionarios);
		return iterator;
	}
}