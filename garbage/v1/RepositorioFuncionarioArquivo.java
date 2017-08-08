package dados.repositorios.funcionario;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import dados.base.pessoas.Endereco;
import dados.base.pessoas.Funcionario;
import dados.base.pessoas.excecoes.PessoaCadastradaException;
import dados.base.pessoas.excecoes.PessoaInexistenteException;
import dados.repositorios.iterator.Iterator;

public class RepositorioFuncionarioArquivo implements RepositorioFuncionario,
		Serializable {
	private static final long serialVersionUID = -5483944738148440384L;
	private HSSFWorkbook hssfWorkbook;
	private HSSFSheet hssfSheet;
	private FileOutputStream output;
	private int indice;

	/**
	 * @param hssfWorkbook
	 * @throws IOException
	 */
	public RepositorioFuncionarioArquivo(HSSFWorkbook hssfWorkbook)
			throws IOException {
		this.hssfWorkbook = hssfWorkbook;
		this.hssfSheet = hssfWorkbook.getSheet("Funcionario");

		if (hssfSheet == null) {
			hssfSheet = hssfWorkbook.createSheet("Funcionario");
			indice = 1;
		} else {
			indice = hssfSheet.getPhysicalNumberOfRows();
		}

		criarCabecalho();
		output = null;
		salvarFuncionario();
	}

	@SuppressWarnings("deprecation")
	private void criarCabecalho() {
		HSSFRow cabecalho = hssfSheet.createRow(0);
		cabecalho.setHeight((short) 265);
		cabecalho.createCell((short) 0).setCellValue("Nome");
		cabecalho.createCell((short) 1).setCellValue("CPF");
		cabecalho.createCell((short) 2).setCellValue("Tempo de trabalho");
		cabecalho.createCell((short) 3).setCellValue("Rua");
		cabecalho.createCell((short) 4).setCellValue("Número");
		cabecalho.createCell((short) 5).setCellValue("Complemento");
		cabecalho.createCell((short) 6).setCellValue("Bairro");
		cabecalho.createCell((short) 7).setCellValue("Cidade");
		cabecalho.createCell((short) 8).setCellValue("Estado");
	}

	private void salvarFuncionario() throws IOException {
		output = new FileOutputStream("Arquivos\\Loja de Fantasias.xls");
		hssfWorkbook.write(output);
		output.close();
	}

	@SuppressWarnings("deprecation")
	public void inserir(Funcionario funcionario)
			throws PessoaCadastradaException, IOException {
		for (int i = 1; i < hssfSheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow linha = hssfSheet.getRow(i);

			if (linha.getCell((short) 1).getStringCellValue()
					.equals(funcionario.getCpf())) {
				throw new PessoaCadastradaException();
			}
		}

		HSSFRow linha = hssfSheet.createRow(indice);
		linha.createCell((short) 0).setCellValue(funcionario.getNome());
		linha.createCell((short) 1).setCellValue(funcionario.getCpf());
		linha.createCell((short) 2).setCellValue(
				funcionario.getTempoAnosTrabalho());

		// atributos de endereco
		linha.createCell((short) 3).setCellValue(
				funcionario.getEndereco().getRua());
		linha.createCell((short) 4).setCellValue(
				funcionario.getEndereco().getNumero());
		linha.createCell((short) 5).setCellValue(
				funcionario.getEndereco().getComplemento());
		linha.createCell((short) 6).setCellValue(
				funcionario.getEndereco().getBairro());
		linha.createCell((short) 7).setCellValue(
				funcionario.getEndereco().getCidade());
		linha.createCell((short) 8).setCellValue(
				funcionario.getEndereco().getEstado());

		indice++;
		output = null;
		salvarFuncionario();
	}

	@SuppressWarnings("deprecation")
	public Funcionario procurar(String cpf) throws PessoaInexistenteException {
		Funcionario retorno = new Funcionario(null, null, null, 0);

		for (int i = 1; i < hssfSheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow linha = hssfSheet.getRow(i);

			if (linha.getCell((short) 1).getStringCellValue().equals(cpf)) {
				String nome = linha.getCell((short) 0).getStringCellValue();
				int tempoTrabalho = (int) linha.getCell((short) 2)
						.getNumericCellValue();

				// atributos de endereco
				String rua = linha.getCell((short) 3).getStringCellValue();
				int numero = (int) linha.getCell((short) 4)
						.getNumericCellValue();
				String complemento = linha.getCell((short) 5)
						.getStringCellValue();
				String bairro = linha.getCell((short) 6).getStringCellValue();
				String cidade = linha.getCell((short) 7).getStringCellValue();
				String estado = linha.getCell((short) 8).getStringCellValue();

				// recuperação das informações do funcionário
				Endereco endereco = new Endereco(rua, numero, complemento,
						bairro, cidade, estado);
				retorno = new Funcionario(nome, cpf, endereco, tempoTrabalho);
			}
		}

		if (retorno.getCpf() == null) {
			throw new PessoaInexistenteException();
		}

		return retorno;
	}

	@SuppressWarnings("deprecation")
	public void remover(String cpf) throws PessoaInexistenteException,
			IOException {
		int linhas = hssfSheet.getPhysicalNumberOfRows();

		for (int i = 1; i < linhas; i++) {
			HSSFRow linha = hssfSheet.getRow(i);
			HSSFRow linha2 = hssfSheet.getRow(i + 1);

			if (linha.getCell((short) 1).getStringCellValue().equals(cpf)) {
				while (linhas > 0) {
					if (linha2 != null) {
						linha.createCell((short) 0).setCellValue(
								linha2.getCell((short) 0).getStringCellValue());
						linha.createCell((short) 1).setCellValue(
								linha2.getCell((short) 1).getStringCellValue());
						linha.createCell((short) 2)
								.setCellValue(
										linha2.getCell((short) 2)
												.getNumericCellValue());

						// atributos de endereco
						linha.createCell((short) 3).setCellValue(
								linha2.getCell((short) 3).getStringCellValue());
						linha.createCell((short) 4)
								.setCellValue(
										linha2.getCell((short) 4)
												.getNumericCellValue());
						linha.createCell((short) 5).setCellValue(
								linha2.getCell((short) 5).getStringCellValue());
						linha.createCell((short) 6).setCellValue(
								linha2.getCell((short) 6).getStringCellValue());
						linha.createCell((short) 7).setCellValue(
								linha2.getCell((short) 7).getStringCellValue());
						linha.createCell((short) 8).setCellValue(
								linha2.getCell((short) 8).getStringCellValue());

						linhas--;
						i++;
						linha = hssfSheet.getRow(i);
						linha2 = hssfSheet.getRow(i + 1);
					} else {
						hssfSheet.removeRow(linha);
						linhas = 0;
					}
				}

				indice--;
			} else {
				throw new PessoaInexistenteException();
			}

			output = null;
			salvarFuncionario();
		}
	}

	@SuppressWarnings("deprecation")
	public void atualizar(Funcionario funcionario)
			throws PessoaInexistenteException, IOException {
		boolean atualizado = false;

		for (int i = 1; i < hssfSheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow linha = hssfSheet.getRow(i);

			if (linha.getCell((short) 1).getStringCellValue()
					.equals(funcionario.getCpf())) {
				linha.createCell((short) 0).setCellValue(funcionario.getNome());
				linha.createCell((short) 2).setCellValue(
						funcionario.getTempoAnosTrabalho());

				// atributos de endereco
				linha.createCell((short) 3).setCellValue(
						funcionario.getEndereco().getRua());
				linha.createCell((short) 4).setCellValue(
						funcionario.getEndereco().getNumero());
				linha.createCell((short) 5).setCellValue(
						funcionario.getEndereco().getComplemento());
				linha.createCell((short) 6).setCellValue(
						funcionario.getEndereco().getBairro());
				linha.createCell((short) 7).setCellValue(
						funcionario.getEndereco().getCidade());
				linha.createCell((short) 8).setCellValue(
						funcionario.getEndereco().getEstado());
				atualizado = true;
			}
		}

		if (!atualizado) {
			throw new PessoaInexistenteException();
		}

		output = null;
		salvarFuncionario();
	}

	public String gerarRelatorio() {
		Iterator<Funcionario> iterator = getIterator();
		String retorno = "";

		while (iterator.hasNext()) {
			retorno = retorno + iterator.next();
		}

		return retorno;
	}

	@SuppressWarnings("deprecation")
	public Iterator<Funcionario> getIterator() {
		Funcionario[] funcionarios = new Funcionario[hssfSheet
				.getPhysicalNumberOfRows()];

		for (int i = 1; i < hssfSheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow linha = hssfSheet.getRow(i);
			String nome = linha.getCell((short) 0).getStringCellValue();
			String cpf = linha.getCell((short) 1).getStringCellValue();
			int tempoTrabalho = (int) linha.getCell((short) 2)
					.getNumericCellValue();
			String rua = linha.getCell((short) 3).getStringCellValue();
			int numero = (int) linha.getCell((short) 4).getNumericCellValue();
			String complemento = linha.getCell((short) 5).getStringCellValue();
			String bairro = linha.getCell((short) 6).getStringCellValue();
			String cidade = linha.getCell((short) 7).getStringCellValue();
			String estado = linha.getCell((short) 8).getStringCellValue();

			Endereco endereco = new Endereco(rua, numero, complemento, bairro,
					cidade, estado);
			funcionarios[i - 1] = new Funcionario(nome, cpf, endereco,
					tempoTrabalho);
		}

		Iterator<Funcionario> iterator = new Iterator<Funcionario>(funcionarios);
		return iterator;
	}
}