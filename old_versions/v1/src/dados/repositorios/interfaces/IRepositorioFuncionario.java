package dados.repositorios.interfaces;

import java.io.IOException;

import dados.base.pessoas.Funcionario;
import dados.base.excecoes.PessoaCadastradaException;
import dados.base.excecoes.PessoaInexistenteException;

public interface IRepositorioFuncionario {
	void inserir(Funcionario funcionario) throws PessoaCadastradaException, IOException;

	Funcionario procurar(String cpf) throws PessoaInexistenteException;

	void remover(String cpf) throws PessoaInexistenteException, IOException;

	void atualizar(Funcionario funcionario) throws PessoaInexistenteException, IOException;

	String gerarRelatorio();
}