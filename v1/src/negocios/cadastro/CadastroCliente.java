package negocios.cadastro;

import java.io.IOException;

import dados.base.excecoes.PessoaCadastradaException;
import dados.base.excecoes.PessoaInexistenteException;
import dados.base.pessoas.Cliente;
import dados.repositorios.interfaces.IRepositorioCliente;
import negocios.excecoes.CpfInvalidoException;
import negocios.excecoes.EnderecoInvalidoException;
import negocios.excecoes.NomeInvalidoException;

public class CadastroCliente {
	private IRepositorioCliente repositorio;

	/**
	 * @param repositorio
	 */
	public CadastroCliente(IRepositorioCliente repositorio) {
		this.repositorio = repositorio;
	}

	/**
	 * Adicionar Cliente
	 * 
	 * @param cliente
	 * @throws NomeInvalidoException
	 * @throws CpfInvalidoException
	 * @throws EnderecoInvalidoException
	 * @throws PessoaCadastradaException
	 * @throws IOException
	 */
	public void adicionar(Cliente cliente) throws NomeInvalidoException, CpfInvalidoException,
			EnderecoInvalidoException, PessoaCadastradaException, IOException {
		if (cliente.getNome().length() > 35 || cliente.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}

		if (cliente.getCpf().length() != 14 || cliente.getCpf().contains(" ")) {
			throw new CpfInvalidoException();
		}

		if (cliente.getEndereco().getRua().length() > 35 || cliente.getEndereco().getRua().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Rua' não foi inserido de forma válida.");
		}

		if (cliente.getEndereco().getBairro().length() > 20 || cliente.getEndereco().getBairro().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Bairro' não foi inserido de forma válida.");
		}

		if (cliente.getEndereco().getCidade().length() > 20 || cliente.getEndereco().getCidade().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Cidade' não foi inserido de forma válida.");
		}

		if (cliente.getEndereco().getEstado().length() > 20 || cliente.getEndereco().getEstado().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Estado' não foi inserido de forma válida.");
		}

		repositorio.inserir(cliente);
	}

	/**
	 * Atualizar Cliente
	 * 
	 * @param cliente
	 * @throws NomeInvalidoException
	 * @throws CpfInvalidoException
	 * @throws EnderecoInvalidoException
	 * @throws PessoaInexistenteException
	 * @throws IOException
	 */
	public void atualizar(Cliente cliente) throws NomeInvalidoException, CpfInvalidoException,
			EnderecoInvalidoException, PessoaInexistenteException, IOException {
		if (cliente.getNome().length() > 35 || cliente.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}

		if (cliente.getCpf().length() != 14) {
			throw new CpfInvalidoException();
		}

		if (cliente.getEndereco().getRua().length() > 35 || cliente.getNome().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Rua' não foi inserido de forma válida.");
		}

		if (cliente.getEndereco().getBairro().length() > 20 || cliente.getNome().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Bairro' não foi inserido de forma válida.");
		}

		if (cliente.getEndereco().getCidade().length() > 20 || cliente.getNome().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Cidade' não foi inserido de forma válida.");
		}

		if (cliente.getEndereco().getEstado().length() > 20 || cliente.getNome().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Estado' não foi inserido de forma válida.");
		}

		repositorio.atualizar(cliente);
	}

	/**
	 * Procurar Cliente
	 * 
	 * @param cpf
	 * @return
	 * @throws PessoaInexistenteException
	 * @throws CpfInvalidoException
	 */
	public Cliente procurar(String cpf) throws PessoaInexistenteException, CpfInvalidoException {
		if (cpf.length() != 14) {
			throw new CpfInvalidoException();
		}

		return repositorio.procurar(cpf);
	}

	/**
	 * Remover Cliente
	 * 
	 * @param cpf
	 * @throws PessoaInexistenteException
	 * @throws IOException
	 * @throws CpfInvalidoException
	 */
	public void remover(String cpf) throws PessoaInexistenteException, IOException, CpfInvalidoException {
		if (cpf.length() != 14) {
			throw new CpfInvalidoException();
		}

		repositorio.remover(cpf);
	}

	/**
	 * Gerar Relatório
	 * 
	 * @return
	 */
	public String gerarRelatorio() {
		return repositorio.gerarRelatorio();
	}
}