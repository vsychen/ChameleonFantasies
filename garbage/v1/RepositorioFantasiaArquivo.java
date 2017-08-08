package dados.repositorios.fantasia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import dados.base.roupas.*;
import dados.base.roupas.excecoes.*;
import dados.repositorios.iterator.*;

public class RepositorioFantasiaArquivo implements RepositorioFantasia,
		Serializable {
	private static final long serialVersionUID = 1539743017819932547L;
	private HSSFWorkbook hssfWorkbook;
	private HSSFSheet hssfSheet;
	private FileOutputStream output;
	private int indice;

	/**
	 * @param hssfWorkbook
	 * @throws IOException
	 */
	public RepositorioFantasiaArquivo(HSSFWorkbook hssfWorkbook)
			throws IOException {
		this.hssfWorkbook = hssfWorkbook;
		this.hssfSheet = hssfWorkbook.getSheet("Fantasia");

		if (hssfSheet == null) {
			hssfSheet = hssfWorkbook.createSheet("Fantasia");
			indice = 1;
		} else {
			indice = hssfSheet.getPhysicalNumberOfRows();
		}

		criarCabecalho();
		output = null;
		salvarFantasia();
	}

	@SuppressWarnings("deprecation")
	private void criarCabecalho() {
		HSSFRow cabecalho = hssfSheet.createRow(0);
		cabecalho.setHeight((short) 265);
		cabecalho.createCell((short) 0).setCellValue("Nome da fantasia");
		cabecalho.createCell((short) 1).setCellValue("Quantidade");
		cabecalho.createCell((short) 2).setCellValue("Cor do chapéu");
		cabecalho.createCell((short) 3)
				.setCellValue("Preço de venda do chapéu");
		cabecalho.createCell((short) 4).setCellValue(
				"Preço de compra do chapéu");
		cabecalho.createCell((short) 5).setCellValue("Enfeites no chapeu");
		cabecalho.createCell((short) 6).setCellValue("Estampas no chapeu");
		cabecalho.createCell((short) 7).setCellValue("Cor da parte de cima");
		cabecalho.createCell((short) 8).setCellValue(
				"Preço de venda da parte de cima");
		cabecalho.createCell((short) 9).setCellValue(
				"Preço de compra da parte de cima");
		cabecalho.createCell((short) 10).setCellValue(
				"Enfeite da parte de cima");
		cabecalho.createCell((short) 11).setCellValue(
				"Estampa da parte de cima");
		cabecalho.createCell((short) 12).setCellValue("Tipo da parte de cima");
		cabecalho.createCell((short) 13).setCellValue("Cor da parte de baixo");
		cabecalho.createCell((short) 14).setCellValue(
				"Preço de venda da parte de baixo");
		cabecalho.createCell((short) 15).setCellValue(
				"Preço de compra da parte de baixo");
		cabecalho.createCell((short) 16).setCellValue(
				"Enfeite da parte de baixo");
		cabecalho.createCell((short) 17).setCellValue(
				"Estampa da parte de baixo");
		cabecalho.createCell((short) 18).setCellValue("Tipo da parte de baixo");
	}

	public void salvarFantasia() throws IOException {
		output = new FileOutputStream("Arquivos\\Loja de Fantasias.xls");
		hssfWorkbook.write(output);
		output.close();
	}

	@SuppressWarnings("deprecation")
	public void inserir(Fantasia fantasia) throws ConjuntoCadastradoException,
			IOException {
		for (int i = 1; i < hssfSheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow linha = hssfSheet.getRow(i);

			if (linha.getCell((short) 0).getStringCellValue()
					.equals(fantasia.getParteBaixo().getNomeConjunto())) {
				throw new ConjuntoCadastradoException();
			}
		}

		HSSFRow linha = hssfSheet.createRow(indice);

		// nomeConjunto && quantidade
		linha.createCell((short) 0).setCellValue(
				fantasia.getParteBaixo().getNomeConjunto());
		linha.createCell((short) 1).setCellValue(fantasia.getQuantidade());

		// atributos de chapeu
		if (fantasia.getChapeu() != null) {
			linha.createCell((short) 2).setCellValue(
					fantasia.getChapeu().getCor());
			linha.createCell((short) 3).setCellValue(
					fantasia.getChapeu().getPrecoVenda());
			linha.createCell((short) 4).setCellValue(
					fantasia.getChapeu().getPrecoCompra());
			linha.createCell((short) 5).setCellValue(
					fantasia.getChapeu().isEnfeite());
			linha.createCell((short) 6).setCellValue(
					fantasia.getChapeu().isEstampa());
		}

		// atributos de parteCima
		if (fantasia.getParteCima() != null) {
			linha.createCell((short) 7).setCellValue(
					fantasia.getParteCima().getCor());
			linha.createCell((short) 8).setCellValue(
					fantasia.getParteCima().getPrecoVenda());
			linha.createCell((short) 9).setCellValue(
					fantasia.getParteCima().getPrecoCompra());
			linha.createCell((short) 10).setCellValue(
					fantasia.getParteCima().isEnfeite());
			linha.createCell((short) 11).setCellValue(
					fantasia.getParteCima().isEstampa());
			linha.createCell((short) 12).setCellValue(
					fantasia.getParteCima().getTipo());
		}

		// atributos de parteBaixo
		linha.createCell((short) 13).setCellValue(
				fantasia.getParteBaixo().getCor());
		linha.createCell((short) 14).setCellValue(
				fantasia.getParteBaixo().getPrecoVenda());
		linha.createCell((short) 15).setCellValue(
				fantasia.getParteBaixo().getPrecoCompra());
		linha.createCell((short) 16).setCellValue(
				fantasia.getParteBaixo().isEnfeite());
		linha.createCell((short) 17).setCellValue(
				fantasia.getParteBaixo().isEstampa());
		linha.createCell((short) 18).setCellValue(
				fantasia.getParteBaixo().getTipo());

		indice++;
		output = null;
		salvarFantasia();
	}

	@SuppressWarnings("deprecation")
	public Fantasia procurar(String nomeConjunto)
			throws ConjuntoInexistenteException {
		ParteBaixo parteBaixo = new ParteBaixo(null, 0, 0, null, false, false,
				null);
		Fantasia retorno = new Fantasia(parteBaixo, 0);

		for (int i = 1; i < hssfSheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow linha = hssfSheet.getRow(i);

			if (linha.getCell((short) 0).getStringCellValue()
					.equalsIgnoreCase(nomeConjunto)) {
				Chapeu chapeu = null;
				ParteCima parteCima = null;

				// quantidade
				int quantidade = (int) linha.getCell((short) 1)
						.getNumericCellValue();

				// atributos de chapeu
				if (linha.getCell((short) 2) != null) {
					String corChapeu = linha.getCell((short) 2)
							.getStringCellValue();
					double precoVendaChapeu = linha.getCell((short) 3)
							.getNumericCellValue();
					double precoCompraChapeu = linha.getCell((short) 4)
							.getNumericCellValue();
					boolean enfeiteChapeu = linha.getCell((short) 5)
							.getBooleanCellValue();
					boolean estampaChapeu = linha.getCell((short) 6)
							.getBooleanCellValue();

					chapeu = new Chapeu(nomeConjunto, precoVendaChapeu,
							precoCompraChapeu, corChapeu, enfeiteChapeu,
							estampaChapeu);
				}

				// atributos de parteCima
				if (linha.getCell((short) 7) != null) {
					String corCima = linha.getCell((short) 7)
							.getStringCellValue();
					double precoVendaCima = linha.getCell((short) 8)
							.getNumericCellValue();
					double precoCompraCima = linha.getCell((short) 9)
							.getNumericCellValue();
					boolean enfeiteCima = linha.getCell((short) 10)
							.getBooleanCellValue();
					boolean estampaCima = linha.getCell((short) 11)
							.getBooleanCellValue();
					String tipoCima = linha.getCell((short) 12)
							.getStringCellValue();

					parteCima = new ParteCima(nomeConjunto, precoVendaCima,
							precoCompraCima, corCima, enfeiteCima, estampaCima,
							tipoCima);
				}

				// atributos de parteBaixo
				String corBaixo = linha.getCell((short) 13)
						.getStringCellValue();
				double precoVendaBaixo = linha.getCell((short) 14)
						.getNumericCellValue();
				double precoCompraBaixo = linha.getCell((short) 15)
						.getNumericCellValue();
				boolean enfeiteBaixo = linha.getCell((short) 16)
						.getBooleanCellValue();
				boolean estampaBaixo = linha.getCell((short) 17)
						.getBooleanCellValue();
				String tipoBaixo = linha.getCell((short) 18)
						.getStringCellValue();

				parteBaixo = new ParteBaixo(nomeConjunto, precoVendaBaixo,
						precoCompraBaixo, corBaixo, enfeiteBaixo, estampaBaixo,
						tipoBaixo);

				// recuperação das informações da fantasia
				if (chapeu == null && parteCima == null) {
					retorno = new Fantasia(parteBaixo, quantidade);
				} else if (chapeu == null && parteCima != null) {
					retorno = new Fantasia(parteCima, parteBaixo, quantidade);
				} else if (chapeu != null && parteCima == null) {
					retorno = new Fantasia(chapeu, parteBaixo, quantidade);
				} else if (chapeu != null && parteCima != null) {
					retorno = new Fantasia(chapeu, parteCima, parteBaixo,
							quantidade);
				}
			}
		}

		if (retorno.getParteBaixo().getNomeConjunto() == null) {
			throw new ConjuntoInexistenteException();
		}

		return retorno;
	}

	@SuppressWarnings("deprecation")
	public void remover(String nomeConjunto)
			throws ConjuntoInexistenteException, IOException {
		int linhas = hssfSheet.getPhysicalNumberOfRows();

		for (int i = 1; i < linhas; i++) {
			HSSFRow linha = hssfSheet.getRow(i);
			HSSFRow linha2 = hssfSheet.getRow(i + 1);

			if (linha.getCell((short) 0).getStringCellValue()
					.equals(nomeConjunto)) {
				while (linhas > 0) {
					if (linha2 != null) {
						// nomeConjunto && quantidade
						linha.createCell((short) 0).setCellValue(
								linha2.getCell((short) 0).getStringCellValue());
						linha.createCell((short) 1)
								.setCellValue(
										linha2.getCell((short) 1)
												.getNumericCellValue());

						// atributos de chapeu
						if (linha2.getCell((short) 2) != null) {
							linha.createCell((short) 2).setCellValue(
									linha2.getCell((short) 2)
											.getStringCellValue());
							linha.createCell((short) 3).setCellValue(
									linha2.getCell((short) 3)
											.getNumericCellValue());
							linha.createCell((short) 4).setCellValue(
									linha2.getCell((short) 4)
											.getNumericCellValue());
							linha.createCell((short) 5).setCellValue(
									linha2.getCell((short) 5)
											.getBooleanCellValue());
							linha.createCell((short) 6).setCellValue(
									linha2.getCell((short) 6)
											.getBooleanCellValue());
						} else {
							if (linha.getCell((short) 2) != null) {
								for (int j = 2; j <= 6; j++) {
									linha.removeCell(linha.getCell((short) j));
								}
							}
						}

						// atributos de parteCima
						if (linha2.getCell((short) 7) != null) {
							linha.createCell((short) 7).setCellValue(
									linha2.getCell((short) 7)
											.getStringCellValue());
							linha.createCell((short) 8).setCellValue(
									linha2.getCell((short) 8)
											.getNumericCellValue());
							linha.createCell((short) 9).setCellValue(
									linha2.getCell((short) 9)
											.getNumericCellValue());
							linha.createCell((short) 10).setCellValue(
									linha2.getCell((short) 10)
											.getBooleanCellValue());
							linha.createCell((short) 11).setCellValue(
									linha2.getCell((short) 11)
											.getBooleanCellValue());
							linha.createCell((short) 12).setCellValue(
									linha2.getCell((short) 12)
											.getStringCellValue());
						} else {
							if (linha.getCell((short) 7) != null) {
								for (int j = 7; j <= 12; j++) {
									linha.removeCell(linha.getCell((short) j));
								}
							}
						}

						// atributos de parteBaixo
						linha.createCell((short) 13)
								.setCellValue(
										linha2.getCell((short) 13)
												.getStringCellValue());
						linha.createCell((short) 14).setCellValue(
								linha2.getCell((short) 14)
										.getNumericCellValue());
						linha.createCell((short) 15).setCellValue(
								linha2.getCell((short) 15)
										.getNumericCellValue());
						linha.createCell((short) 16).setCellValue(
								linha2.getCell((short) 16)
										.getBooleanCellValue());
						linha.createCell((short) 17).setCellValue(
								linha2.getCell((short) 17)
										.getBooleanCellValue());
						linha.createCell((short) 18)
								.setCellValue(
										linha2.getCell((short) 18)
												.getStringCellValue());

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
				throw new ConjuntoInexistenteException();
			}

			output = null;
			salvarFantasia();
		}
	}

	@SuppressWarnings("deprecation")
	public void atualizar(Fantasia fantasia)
			throws ConjuntoInexistenteException, IOException {
		boolean atualizado = false;
		for (int i = 1; i < hssfSheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow linha = hssfSheet.getRow(i);

			if (linha.getCell((short) 0).getStringCellValue()
					.equals(fantasia.getParteBaixo().getNomeConjunto())) {
				// nomeConjunto && quantidade
				linha.createCell((short) 0).setCellValue(
						fantasia.getParteBaixo().getNomeConjunto());
				linha.createCell((short) 1).setCellValue(
						fantasia.getQuantidade());

				// atributos de chapeu
				if (fantasia.getChapeu() != null) {
					linha.createCell((short) 2).setCellValue(
							fantasia.getChapeu().getCor());
					linha.createCell((short) 3).setCellValue(
							fantasia.getChapeu().getPrecoVenda());
					linha.createCell((short) 4).setCellValue(
							fantasia.getChapeu().getPrecoCompra());
					linha.createCell((short) 5).setCellValue(
							fantasia.getChapeu().isEnfeite());
					linha.createCell((short) 6).setCellValue(
							fantasia.getChapeu().isEstampa());
				} else {
					if (linha.getCell((short) 2) != null) {
						for (int j = 2; j <= 6; j++) {
							HSSFCell cell = linha.getCell(j);
							linha.removeCell(cell);
						}
					}
				}

				// atributos de parteCima
				if (fantasia.getParteCima() != null) {
					linha.createCell((short) 7).setCellValue(
							fantasia.getParteCima().getCor());
					linha.createCell((short) 8).setCellValue(
							fantasia.getParteCima().getPrecoVenda());
					linha.createCell((short) 9).setCellValue(
							fantasia.getParteCima().getPrecoCompra());
					linha.createCell((short) 10).setCellValue(
							fantasia.getParteCima().isEnfeite());
					linha.createCell((short) 11).setCellValue(
							fantasia.getParteCima().isEstampa());
					linha.createCell((short) 12).setCellValue(
							fantasia.getParteCima().getTipo());
				} else {
					if (linha.getCell((short) 7) != null) {
						for (int j = 7; j <= 12; j++) {
							HSSFCell cell = linha.getCell(j);
							linha.removeCell(cell);
						}
					}
				}

				// atributos de parteBaixo
				linha.createCell((short) 13).setCellValue(
						fantasia.getParteBaixo().getCor());
				linha.createCell((short) 14).setCellValue(
						fantasia.getParteBaixo().getPrecoVenda());
				linha.createCell((short) 15).setCellValue(
						fantasia.getParteBaixo().getPrecoCompra());
				linha.createCell((short) 16).setCellValue(
						fantasia.getParteBaixo().isEnfeite());
				linha.createCell((short) 17).setCellValue(
						fantasia.getParteBaixo().isEstampa());
				linha.createCell((short) 18).setCellValue(
						fantasia.getParteBaixo().getTipo());
				atualizado = true;
			}
		}

		if (!atualizado) {
			throw new ConjuntoInexistenteException();
		}

		output = null;
		salvarFantasia();
	}

	public String gerarRelatorio() {
		Iterator<Fantasia> iterator = getIterator();
		String retorno = "";

		while (iterator.hasNext()) {
			retorno = retorno + iterator.next();
		}

		return retorno;
	}

	@SuppressWarnings("deprecation")
	public Iterator<Fantasia> getIterator() {
		Fantasia[] fantasias = new Fantasia[hssfSheet.getPhysicalNumberOfRows()];

		for (int i = 1; i < hssfSheet.getPhysicalNumberOfRows(); i++) {
			HSSFRow linha = hssfSheet.getRow(i);
			String nomeConjunto = linha.getCell((short) 0).getStringCellValue();
			int quantidade = (int) linha.getCell((short) 1)
					.getNumericCellValue();
			Chapeu chapeu = null;
			ParteCima parteCima = null;

			if (linha.getCell((short) 2) != null) {
				String corChapeu = linha.getCell((short) 2)
						.getStringCellValue();
				double precoVendaChapeu = linha.getCell((short) 3)
						.getNumericCellValue();
				double precoCompraChapeu = linha.getCell((short) 4)
						.getNumericCellValue();
				boolean enfeiteChapeu = linha.getCell((short) 5)
						.getBooleanCellValue();
				boolean estampaChapeu = linha.getCell((short) 6)
						.getBooleanCellValue();

				chapeu = new Chapeu(nomeConjunto, precoVendaChapeu,
						precoCompraChapeu, corChapeu, enfeiteChapeu,
						estampaChapeu);
			}

			if (linha.getCell((short) 7) != null) {
				String corCima = linha.getCell((short) 7).getStringCellValue();
				double precoVendaCima = linha.getCell((short) 8)
						.getNumericCellValue();
				double precoCompraCima = linha.getCell((short) 9)
						.getNumericCellValue();
				boolean enfeiteCima = linha.getCell((short) 10)
						.getBooleanCellValue();
				boolean estampaCima = linha.getCell((short) 11)
						.getBooleanCellValue();
				String tipoCima = linha.getCell((short) 12)
						.getStringCellValue();

				parteCima = new ParteCima(nomeConjunto, precoVendaCima,
						precoCompraCima, corCima, enfeiteCima, estampaCima,
						tipoCima);
			}

			String corBaixo = linha.getCell((short) 13).getStringCellValue();
			double precoVendaBaixo = linha.getCell((short) 14)
					.getNumericCellValue();
			double precoCompraBaixo = linha.getCell((short) 15)
					.getNumericCellValue();
			boolean enfeiteBaixo = linha.getCell((short) 16)
					.getBooleanCellValue();
			boolean estampaBaixo = linha.getCell((short) 17)
					.getBooleanCellValue();
			String tipoBaixo = linha.getCell((short) 18).getStringCellValue();

			ParteBaixo parteBaixo = new ParteBaixo(nomeConjunto,
					precoVendaBaixo, precoCompraBaixo, corBaixo, enfeiteBaixo,
					estampaBaixo, tipoBaixo);
			fantasias[i - 1] = new Fantasia(chapeu, parteCima, parteBaixo,
					quantidade);
		}

		Iterator<Fantasia> iterator = new Iterator<Fantasia>(fantasias);
		return iterator;
	}
}