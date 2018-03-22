package negocios.fachada;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dados.base.excecoes.ConjuntoCadastradoException;
import dados.base.excecoes.ConjuntoInexistenteException;
import dados.base.excecoes.PessoaCadastradaException;
import dados.base.excecoes.PessoaInexistenteException;
import dados.base.pessoas.Cliente;
import dados.base.pessoas.Funcionario;
import dados.base.roupas.Fantasia;
import dados.repositorios.array.RepositorioArrayCliente;
import dados.repositorios.array.RepositorioArrayFantasia;
import dados.repositorios.array.RepositorioArrayFuncionario;
import dados.repositorios.interfaces.IRepositorioCliente;
import dados.repositorios.interfaces.IRepositorioFantasia;
import dados.repositorios.interfaces.IRepositorioFuncionario;
import dados.repositorios.vetor.RepositorioVetorCliente;
import dados.repositorios.vetor.RepositorioVetorFantasia;
import dados.repositorios.vetor.RepositorioVetorFuncionario;
import negocios.cadastro.CadastroCliente;
import negocios.cadastro.CadastroFantasia;
import negocios.cadastro.CadastroFuncionario;
import negocios.excecoes.CorInvalidaException;
import negocios.excecoes.CpfInvalidoException;
import negocios.excecoes.EnderecoInvalidoException;
import negocios.excecoes.NomeConjuntoDiferentesException;
import negocios.excecoes.NomeConjuntoInvalidoException;
import negocios.excecoes.NomeInvalidoException;
import negocios.excecoes.PrecoInvalidoException;
import negocios.excecoes.QuantidadeInvalidaException;
import negocios.excecoes.TempoTrabalhoInvalidoException;
import negocios.excecoes.TipoRoupaInvalidoException;
import negocios.excecoes.TxtInvalidoException;

public class Fachada {
	private IRepositorioFantasia fantasias;
	private IRepositorioCliente clientes;
	private IRepositorioFuncionario funcionarios;
	private CadastroFantasia cadastroFantasias;
	private CadastroCliente cadastroClientes;
	private CadastroFuncionario cadastroFuncionarios;
	private String caminhoArquivo = "../v1/arquivos_externos/";
	private String caminhoImagem = "../v1/img/";

	/**
	 * @throws IOException
	 * @throws TxtInvalidoException
	 */
	public Fachada() throws IOException, TxtInvalidoException {
		BufferedReader leitor = new BufferedReader(new FileReader(this.getCaminhoArquivo() + "config.txt"));
		String tipoRepositorio = leitor.readLine();

		if (tipoRepositorio.equalsIgnoreCase("array")) {
			clientes = new RepositorioArrayCliente();
			funcionarios = new RepositorioArrayFuncionario();
			fantasias = new RepositorioArrayFantasia();
		} else if (tipoRepositorio.equalsIgnoreCase("lista") || tipoRepositorio.equalsIgnoreCase("vetor")) {
			clientes = new RepositorioVetorCliente();
			funcionarios = new RepositorioVetorFuncionario();
			fantasias = new RepositorioVetorFantasia();
		} else {
			leitor.close();
			throw new TxtInvalidoException();
		}

		cadastroClientes = new CadastroCliente(clientes);
		cadastroFuncionarios = new CadastroFuncionario(funcionarios);
		cadastroFantasias = new CadastroFantasia(fantasias);
		leitor.close();
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	/**
	 * Cadastrar Fantasia
	 * 
	 * @param fantasia
	 * @throws QuantidadeInvalidaException
	 * @throws CorInvalidaException
	 * @throws PrecoInvalidoException
	 * @throws TipoRoupaInvalidoException
	 * @throws NomeConjuntoInvalidoException
	 * @throws NomeConjuntoDiferentesException
	 * @throws ConjuntoCadastradoException
	 * @throws IOException
	 */
	public void cadastrarFantasia(Fantasia fantasia) throws QuantidadeInvalidaException, CorInvalidaException,
			PrecoInvalidoException, TipoRoupaInvalidoException, NomeConjuntoInvalidoException,
			NomeConjuntoDiferentesException, ConjuntoCadastradoException, IOException {
		cadastroFantasias.adicionar(fantasia);
	}

	/**
	 * Atualizar Fantasia
	 * 
	 * @param fantasia
	 * @throws QuantidadeInvalidaException
	 * @throws CorInvalidaException
	 * @throws PrecoInvalidoException
	 * @throws TipoRoupaInvalidoException
	 * @throws NomeConjuntoInvalidoException
	 * @throws NomeConjuntoDiferentesException
	 * @throws ConjuntoInexistenteException
	 * @throws IOException
	 */
	public void atualizarFantasia(Fantasia fantasia) throws QuantidadeInvalidaException, CorInvalidaException,
			PrecoInvalidoException, TipoRoupaInvalidoException, NomeConjuntoInvalidoException,
			NomeConjuntoDiferentesException, ConjuntoInexistenteException, IOException {
		cadastroFantasias.atualizar(fantasia);
	}

	/**
	 * Remover Fantasia
	 * 
	 * @param nomeConjunto
	 * @throws ConjuntoInexistenteException
	 * @throws IOException
	 * @throws NomeConjuntoInvalidoException
	 */
	public void removerFantasia(String nomeConjunto)
			throws ConjuntoInexistenteException, IOException, NomeConjuntoInvalidoException {
		cadastroFantasias.remover(nomeConjunto);
	}

	/**
	 * Procurar Fantasia
	 * 
	 * @param nomeConjunto
	 * @return
	 * @throws ConjuntoInexistenteException
	 * @throws NomeConjuntoInvalidoException
	 */
	public Fantasia procurarFantasia(String nomeConjunto)
			throws ConjuntoInexistenteException, NomeConjuntoInvalidoException {
		return cadastroFantasias.procurar(nomeConjunto);
	}

	/**
	 * Vender Fantasia
	 * 
	 * @param fantasia
	 * @param vendidos
	 * @throws QuantidadeInvalidaException
	 * @throws ConjuntoInexistenteException
	 * @throws IOException
	 */
	public void vender(String nomeFantasia, int vendidos)
			throws QuantidadeInvalidaException, ConjuntoInexistenteException, IOException {
		cadastroFantasias.vender(nomeFantasia, vendidos);
	}

	/**
	 * Comprar Fantasia
	 * 
	 * @param fantasia
	 * @param comprados
	 * @throws QuantidadeInvalidaException
	 * @throws ConjuntoInexistenteException
	 * @throws IOException
	 */
	public void comprar(String nomeFantasia, int comprados)
			throws QuantidadeInvalidaException, ConjuntoInexistenteException, IOException {
		cadastroFantasias.aumentarEstoque(nomeFantasia, comprados);
	}

	/**
	 * Gerar Receita
	 * 
	 * @return
	 */
	public String gerarReceita() {
		return cadastroFantasias.gerarReceita();
	}

	/**
	 * Gerar Relatório Fantasia
	 * 
	 * @return
	 * @throws IOException
	 */
	public String gerarRelatorioFantasia() throws IOException {
		salvarRelatorio(
				cadastroFantasias.gerarRelatorio() + "------------------------------------------------------------\n");
		return cadastroFantasias.gerarRelatorio();
	}

	/**
	 * Cadastrar Cliente
	 * 
	 * @param cliente
	 * @throws NomeInvalidoException
	 * @throws CpfInvalidoException
	 * @throws EnderecoInvalidoException
	 * @throws PessoaCadastradaException
	 * @throws IOException
	 */
	public void cadastrarCliente(Cliente cliente) throws NomeInvalidoException, CpfInvalidoException,
			EnderecoInvalidoException, PessoaCadastradaException, IOException {
		cadastroClientes.adicionar(cliente);
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
	public void atualizarCliente(Cliente cliente) throws NomeInvalidoException, CpfInvalidoException,
			EnderecoInvalidoException, PessoaInexistenteException, IOException {
		cadastroClientes.atualizar(cliente);
	}

	/**
	 * Remover Cliente
	 * 
	 * @param cpf
	 * @throws PessoaInexistenteException
	 * @throws IOException
	 * @throws CpfInvalidoException
	 */
	public void removerCliente(String cpf) throws PessoaInexistenteException, IOException, CpfInvalidoException {
		cadastroClientes.remover(cpf);
	}

	/**
	 * Procurar Cliente
	 * 
	 * @param cpf
	 * @return
	 * @throws PessoaInexistenteException
	 * @throws CpfInvalidoException
	 */
	public Cliente procurarCliente(String cpf) throws PessoaInexistenteException, CpfInvalidoException {
		return cadastroClientes.procurar(cpf);
	}

	/**
	 * Gerar Relatório Cliente
	 * 
	 * @return
	 * @throws IOException
	 */
	public String gerarRelatorioCliente() throws IOException {
		salvarRelatorio(
				cadastroClientes.gerarRelatorio() + "------------------------------------------------------------\n");
		return cadastroClientes.gerarRelatorio();
	}

	/**
	 * Cadastrar Funcionário
	 * 
	 * @param funcionario
	 * @throws NomeInvalidoException
	 * @throws CpfInvalidoException
	 * @throws EnderecoInvalidoException
	 * @throws TempoTrabalhoInvalidoException
	 * @throws PessoaCadastradaException
	 * @throws IOException
	 */
	public void cadastrarFuncionario(Funcionario funcionario) throws NomeInvalidoException, CpfInvalidoException,
			EnderecoInvalidoException, TempoTrabalhoInvalidoException, PessoaCadastradaException, IOException {
		cadastroFuncionarios.adicionar(funcionario);
	}

	/**
	 * Atualizar Funcionário
	 * 
	 * @param funcionario
	 * @throws NomeInvalidoException
	 * @throws CpfInvalidoException
	 * @throws EnderecoInvalidoException
	 * @throws TempoTrabalhoInvalidoException
	 * @throws PessoaInexistenteException
	 * @throws IOException
	 */
	public void atualizarFuncionario(Funcionario funcionario) throws NomeInvalidoException, CpfInvalidoException,
			EnderecoInvalidoException, TempoTrabalhoInvalidoException, PessoaInexistenteException, IOException {
		cadastroFuncionarios.atualizar(funcionario);
	}

	/**
	 * Remover Funcionário
	 * 
	 * @param cpf
	 * @throws PessoaInexistenteException
	 * @throws IOException
	 * @throws CpfInvalidoException
	 */
	public void removerFuncionario(String cpf) throws PessoaInexistenteException, IOException, CpfInvalidoException {
		cadastroFuncionarios.remover(cpf);
	}

	/**
	 * Procurar Funcionário
	 * 
	 * @param cpf
	 * @return
	 * @throws PessoaInexistenteException
	 * @throws CpfInvalidoException
	 */
	public Funcionario procurarFuncionario(String cpf) throws PessoaInexistenteException, CpfInvalidoException {
		return cadastroFuncionarios.procurar(cpf);
	}

	/**
	 * Gerar Relatório Funcionário
	 * 
	 * @return
	 * @throws IOException
	 */
	public String gerarRelatorioFuncionario() throws IOException {
		salvarRelatorio(
				cadastroFantasias.gerarRelatorio() + "------------------------------------------------------------\n");
		return cadastroFuncionarios.gerarRelatorio();
	}

	/**
	 * Salvar Relatório
	 * 
	 * O método salvarRelatorio irá salvar o relatório de determinada classe em
	 * um arquivo .txt para que possa ser consultado futuramente. O parâmetro
	 * escolhido para a ordenação é a ordem de inserção de determinada
	 * informação.
	 * 
	 * @param info
	 * @throws IOException
	 */
	public void salvarRelatorio(String info) throws IOException {
		File text = new File(this.getCaminhoArquivo() + "relatorios.ip.txt");
		String textoAntigo = "";

		if (!text.exists()) {
			text.createNewFile();
		} else {
			FileReader leitor = new FileReader(text);
			BufferedReader reader = new BufferedReader(leitor);
			String linha = reader.readLine();
			boolean fim = false;

			while (!fim) {
				if (linha != null) {
					textoAntigo = textoAntigo + linha + "\r\n";
					linha = reader.readLine();
				} else {
					fim = true;
				}
			}

			reader.close();
		}

		FileWriter escritor = new FileWriter(this.getCaminhoArquivo() + "relatorios.ip.txt");
		PrintWriter relatorio = new PrintWriter(escritor);

		relatorio.write(textoAntigo + info + "\r\n");
		escritor.close();
	}
}