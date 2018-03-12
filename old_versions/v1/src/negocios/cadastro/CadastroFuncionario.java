package negocios.cadastro;

import java.io.IOException;

import negocios.excecoes.CpfInvalidoException;
import negocios.excecoes.EnderecoInvalidoException;
import negocios.excecoes.NomeInvalidoException;
import negocios.excecoes.TempoTrabalhoInvalidoException;
import dados.base.excecoes.PessoaCadastradaException;
import dados.base.excecoes.PessoaInexistenteException;
import dados.base.pessoas.Funcionario;
import dados.repositorios.interfaces.IRepositorioFuncionario;

public class CadastroFuncionario {
	private IRepositorioFuncionario repositorio;

	/**
	 * @param repositorio
	 */
	public CadastroFuncionario(IRepositorioFuncionario repositorio) {
		this.repositorio = repositorio;
	}

	/**
	 * Adicionar Funcion�rio
	 * 
	 * @param funcionario
	 * @throws NomeInvalidoException
	 * @throws CpfInvalidoException
	 * @throws EnderecoInvalidoException
	 * @throws TempoTrabalhoInvalidoException
	 * @throws PessoaCadastradaException
	 * @throws IOException
	 */
	public void adicionar(Funcionario funcionario) throws NomeInvalidoException, CpfInvalidoException,
			EnderecoInvalidoException, TempoTrabalhoInvalidoException, PessoaCadastradaException, IOException {
		if (funcionario.getNome().length() > 35 || funcionario.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}

		if (funcionario.getCpf().length() != 14 || funcionario.getCpf().contains(" ")) {
			throw new CpfInvalidoException();
		}

		if (funcionario.getEndereco().getRua().length() > 35 || funcionario.getEndereco().getRua().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Rua' n�o foi inserido de forma v�lida.");
		}

		if (funcionario.getEndereco().getBairro().length() > 20 || funcionario.getEndereco().getBairro().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Bairro' n�o foi inserido de forma v�lida.");
		}

		if (funcionario.getEndereco().getCidade().length() > 20 || funcionario.getEndereco().getCidade().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Cidade' n�o foi inserido de forma v�lida.");
		}

		if (funcionario.getEndereco().getEstado().length() > 20 || funcionario.getEndereco().getEstado().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Estado' n�o foi inserido de forma v�lida.");
		}

		if (funcionario.getTempoAnosTrabalho() > 100 || funcionario.getTempoAnosTrabalho() < 1) {
			throw new TempoTrabalhoInvalidoException();
		}

		repositorio.inserir(funcionario);
	}

	/**
	 * Atualizar Funcion�rio
	 * 
	 * @param funcionario
	 * @throws NomeInvalidoException
	 * @throws CpfInvalidoException
	 * @throws EnderecoInvalidoException
	 * @throws TempoTrabalhoInvalidoException
	 * @throws PessoaInexistenteException
	 * @throws IOException
	 */
	public void atualizar(Funcionario funcionario) throws NomeInvalidoException, CpfInvalidoException,
			EnderecoInvalidoException, TempoTrabalhoInvalidoException, PessoaInexistenteException, IOException {
		if (funcionario.getNome().length() > 35 || funcionario.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}

		if (funcionario.getCpf().length() != 14) {
			throw new CpfInvalidoException();
		}

		if (funcionario.getEndereco().getRua().length() > 35 || funcionario.getNome().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Rua' n�o foi inserido de forma v�lida.");
		}

		if (funcionario.getEndereco().getBairro().length() > 20 || funcionario.getNome().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Bairro' n�o foi inserido de forma v�lida.");
		}

		if (funcionario.getEndereco().getCidade().length() > 20 || funcionario.getNome().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Cidade' n�o foi inserido de forma v�lida.");
		}

		if (funcionario.getEndereco().getEstado().length() > 20 || funcionario.getNome().isEmpty()) {
			throw new EnderecoInvalidoException("O campo 'Estado' n�o foi inserido de forma v�lida.");
		}

		if (funcionario.getTempoAnosTrabalho() > 100 || funcionario.getTempoAnosTrabalho() < 1) {
			throw new TempoTrabalhoInvalidoException();
		}

		repositorio.atualizar(funcionario);
	}

	/**
	 * Procurar Funcion�rio
	 * 
	 * @param cpf
	 * @return
	 * @throws PessoaInexistenteException
	 * @throws CpfInvalidoException
	 */
	public Funcionario procurar(String cpf) throws PessoaInexistenteException, CpfInvalidoException {
		if (cpf.length() != 14) {
			throw new CpfInvalidoException();
		}

		return repositorio.procurar(cpf);
	}

	/**
	 * Remover Funcion�rio
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
	 * Gerar Relat�rio
	 * 
	 * @return
	 */
	public String gerarRelatorio() {
		return repositorio.gerarRelatorio();
	}
}