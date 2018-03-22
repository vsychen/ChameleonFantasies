package dados.repositorios.cliente;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import dados.base.pessoas.Cliente;
import dados.base.pessoas.Endereco;
import dados.base.pessoas.excecoes.*;
import dados.repositorios.iterator.Iterator;

public class RepositorioClienteArquivo implements RepositorioCliente,
		Serializable {
	private static final long serialVersionUID = 788410730827825398L;
	private HSSFWorkbook hssfWorkbook;
	private HSSFSheet hssfSheet;
	private FileOutputStream output;
	private int indice;

	/**
	 * @param hssfWorkbook
	 * @throws IOException
	 */
	public RepositorioClienteArquivo(HSSFWorkbook hssfWorkbook)
			throws IOException {
		this.hssfWorkbook = hssfWorkbook;
		this.hssfSheet = hssfWorkbook.getSheet("Cliente");

		if (hssfSheet == null) {
			hssfSheet = hssfWorkbook.createSheet("Cliente");
			indice = 1;
		} else {
			indice = hssfSheet.getPhysicalNumberOfRows();
		}

		criarCabecalho();
		output = null;
		salvarCliente();
	}

	@SuppressWarnings("deprecation")
	private void criarCabecalho() {
		HSSFRow cabecalho = hssfSheet.createRow(0);
		cabecalho.setHeight((short) 265);
		cabecalho.createCell((short) 0).setCellValue("Nome");
		cabecalho.createCell((short) 1).setCellValue("CPF");
		cabecalho.createCell((short) 2).setCellValue("Rua");
		cabecalho.createCell((short) 3).setCellValue("Número");
		cabecalho.createCell((short) 4).setCellValue("Complemento");
		cabecalho.createCell((short) 5).setCellValue("Bairro");
		cabecalho.createCell((short) 6).setCellValue("Cidade");
		cabecalho.createCell((short) 7).setCellValue("Estado");
	}

	private void salvarCliente() throws IOException {
		output = new FileOutputStream("Arquivos\\Loja de Fantasias.xls");
		hssfWorkbook.write(output);
		output.close();
	}

	@SuppressWarnings("deprecation")
	public void inserir(Cliente cliente) throws PessoaCadastradaException,
			IOException {
		for (int i = 1; i < hssfSheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow linha = hssfSheet.getRow(i);

			if (linha.getCell((short) 1).getStringCellValue()
					.equals(cliente.getCpf())) {
				throw new PessoaCadastradaException();
			}
		}

		HSSFRow linha = hssfSheet.createRow(indice);
		linha.createCell((short) 0).setCellValue(cliente.getNome());
		linha.createCell((short) 1).setCellValue(cliente.getCpf());

		// atributos de endereco
		linha.createCell((short) 2)
				.setCellValue(cliente.getEndereco().getRua());
		linha.createCell((short) 3).setCellValue(
				cliente.getEndereco().getNumero());
		linha.createCell((short) 4).setCellValue(
				cliente.getEndereco().getComplemento());
		linha.createCell((short) 5).setCellValue(
				cliente.getEndereco().getBairro());
		linha.createCell((short) 6).setCellValue(
				cliente.getEndereco().getCidade());
		linha.createCell((short) 7).setCellValue(
				cliente.getEndereco().getEstado());

		indice++;
		output = null;
		salvarCliente();
	}

	@SuppressWarnings("deprecation")
	public Cliente procurar(String cpf) throws PessoaInexistenteException {
		Cliente retorno = new Cliente(null, null, null);

		for (int i = 1; i < hssfSheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow linha = hssfSheet.getRow(i);

			if (linha.getCell((short) 1).getStringCellValue().equals(cpf)) {
				String nome = linha.getCell((short) 0).getStringCellValue();

				// atributos de endereco
				String rua = linha.getCell((short) 2).getStringCellValue();
				int numero = (int) linha.getCell((short) 3)
						.getNumericCellValue();
				String complemento = linha.getCell((short) 4)
						.getStringCellValue();
				String bairro = linha.getCell((short) 5).getStringCellValue();
				String cidade = linha.getCell((short) 6).getStringCellValue();
				String estado = linha.getCell((short) 7).getStringCellValue();

				// recuperação das informações do cliente
				Endereco endereco = new Endereco(rua, numero, complemento,
						bairro, cidade, estado);
				retorno = new Cliente(nome, cpf, endereco);
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

						// atributos de endereco
						linha.createCell((short) 2).setCellValue(
								linha2.getCell((short) 2).getStringCellValue());
						linha.createCell((short) 3)
								.setCellValue(
										linha2.getCell((short) 3)
												.getNumericCellValue());
						linha.createCell((short) 4).setCellValue(
								linha2.getCell((short) 4).getStringCellValue());
						linha.createCell((short) 5).setCellValue(
								linha2.getCell((short) 5).getStringCellValue());
						linha.createCell((short) 6).setCellValue(
								linha2.getCell((short) 6).getStringCellValue());
						linha.createCell((short) 7).setCellValue(
								linha2.getCell((short) 7).getStringCellValue());

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
			salvarCliente();
		}
	}

	@SuppressWarnings("deprecation")
	public void atualizar(Cliente cliente) throws PessoaInexistenteException,
			IOException {
		boolean atualizado = false;

		for (int i = 1; i < hssfSheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow linha = hssfSheet.getRow(i);

			if (linha.getCell((short) 1).getStringCellValue()
					.equals(cliente.getCpf())) {
				linha.createCell((short) 0).setCellValue(cliente.getNome());

				// atributos de endereco
				linha.createCell((short) 2).setCellValue(
						cliente.getEndereco().getRua());
				linha.createCell((short) 3).setCellValue(
						cliente.getEndereco().getNumero());
				linha.createCell((short) 4).setCellValue(
						cliente.getEndereco().getComplemento());
				linha.createCell((short) 5).setCellValue(
						cliente.getEndereco().getBairro());
				linha.createCell((short) 6).setCellValue(
						cliente.getEndereco().getCidade());
				linha.createCell((short) 7).setCellValue(
						cliente.getEndereco().getEstado());
				atualizado = true;
			}
		}

		if (!atualizado) {
			throw new PessoaInexistenteException();
		}

		output = null;
		salvarCliente();
	}

	public String gerarRelatorio() {
		Iterator<Cliente> iterator = getIterator();
		String retorno = "";

		while (iterator.hasNext()) {
			retorno = retorno + iterator.next();
		}

		return retorno;
	}

	@SuppressWarnings("deprecation")
	public Iterator<Cliente> getIterator() {
		Cliente[] clientes = new Cliente[hssfSheet.getPhysicalNumberOfRows()];

		for (int i = 1; i < hssfSheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow linha = hssfSheet.getRow(i);
			String nome = linha.getCell((short) 0).getStringCellValue();
			String cpf = linha.getCell((short) 1).getStringCellValue();
			String rua = linha.getCell((short) 2).getStringCellValue();
			int numero = (int) linha.getCell((short) 3).getNumericCellValue();
			String complemento = linha.getCell((short) 4).getStringCellValue();
			String bairro = linha.getCell((short) 5).getStringCellValue();
			String cidade = linha.getCell((short) 6).getStringCellValue();
			String estado = linha.getCell((short) 7).getStringCellValue();

			Endereco endereco = new Endereco(rua, numero, complemento, bairro,
					cidade, estado);
			clientes[i - 1] = new Cliente(nome, cpf, endereco);
		}

		Iterator<Cliente> iterator = new Iterator<Cliente>(clientes);
		return iterator;
	}
}