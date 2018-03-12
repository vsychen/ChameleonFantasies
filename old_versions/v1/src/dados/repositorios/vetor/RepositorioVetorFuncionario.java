package dados.repositorios.vetor;

import java.util.Vector;
import java.io.Serializable;

import dados.repositorios.interfaces.IRepositorioFuncionario;
import dados.repositorios.iterator.Iterator;
import dados.base.pessoas.Funcionario;
import dados.base.excecoes.PessoaCadastradaException;
import dados.base.excecoes.PessoaInexistenteException;

public class RepositorioVetorFuncionario implements IRepositorioFuncionario, Serializable {
	private static final long serialVersionUID = 580137986799831134L;
	private Vector<Funcionario> funcionarios;

	public RepositorioVetorFuncionario() {
		funcionarios = new Vector<Funcionario>();
	}

	public void inserir(Funcionario funcionario) throws PessoaCadastradaException {
		Funcionario temp;

		for (int i = 0; i < funcionarios.size(); i++) {
			temp = funcionarios.get(i);

			if (temp.getCpf().equals(funcionario.getCpf())) {
				throw new PessoaCadastradaException();
			}
		}

		funcionarios.add(funcionario);
	}

	public Funcionario procurar(String cpf) throws PessoaInexistenteException {
		Funcionario funcionario = null;
		boolean achou = false;

		for (int i = 0; i < funcionarios.size(); i++) {
			funcionario = funcionarios.get(i);

			if (funcionario.getCpf().equals(cpf)) {
				i = funcionarios.size();
				achou = true;
			}
		}

		if (!achou) {
			throw new PessoaInexistenteException();
		}

		return funcionario;
	}

	public void remover(String cpf) throws PessoaInexistenteException {
		boolean achou = false;

		for (int i = 0; i < funcionarios.size(); i++) {
			Funcionario funcionario = funcionarios.get(i);

			if (funcionario.getCpf().equals(cpf)) {
				funcionarios.remove(i);
				achou = true;
			}
		}

		if (!achou) {
			throw new PessoaInexistenteException();
		}
	}

	public void atualizar(Funcionario funcionario) throws PessoaInexistenteException {
		Funcionario temp = null;
		boolean achou = false;

		for (int i = 0; i < funcionarios.size(); i++) {
			temp = funcionarios.get(i);

			if (temp.getCpf().equals(funcionario.getCpf())) {
				funcionarios.set(i, funcionario);
				achou = true;
			}
		}

		if (!achou) {
			throw new PessoaInexistenteException();
		}
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