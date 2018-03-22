package dados.repositorios.interfaces;

import java.io.IOException;

import dados.base.pessoas.Cliente;
import dados.base.excecoes.PessoaCadastradaException;
import dados.base.excecoes.PessoaInexistenteException;

public interface IRepositorioCliente {
	void inserir(Cliente cliente) throws PessoaCadastradaException, IOException;

	Cliente procurar(String cpf) throws PessoaInexistenteException;

	void remover(String cpf) throws PessoaInexistenteException, IOException;

	void atualizar(Cliente cliente) throws PessoaInexistenteException, IOException;

	String gerarRelatorio();
}