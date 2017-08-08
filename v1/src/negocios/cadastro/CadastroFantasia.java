package negocios.cadastro;

import java.io.IOException;

import dados.base.excecoes.ConjuntoCadastradoException;
import dados.base.excecoes.ConjuntoInexistenteException;
import dados.base.roupas.Fantasia;
import dados.repositorios.interfaces.IRepositorioFantasia;
import negocios.excecoes.CorInvalidaException;
import negocios.excecoes.NomeConjuntoDiferentesException;
import negocios.excecoes.NomeConjuntoInvalidoException;
import negocios.excecoes.PrecoInvalidoException;
import negocios.excecoes.QuantidadeInvalidaException;
import negocios.excecoes.TipoRoupaInvalidoException;

public class CadastroFantasia {
	private IRepositorioFantasia repositorio;
	private double valorVendas;
	private double valorCompras;

	/**
	 * @param repositorio
	 */
	public CadastroFantasia(IRepositorioFantasia repositorio) {
		this.repositorio = repositorio;
	}

	/**
	 * Adicionar Fantasia
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
	public void adicionar(Fantasia fantasia) throws QuantidadeInvalidaException, CorInvalidaException,
			PrecoInvalidoException, TipoRoupaInvalidoException, NomeConjuntoInvalidoException,
			NomeConjuntoDiferentesException, ConjuntoCadastradoException, IOException {
		if (fantasia.getQuantidade() < 1 || fantasia.getQuantidade() > 1000) {
			throw new QuantidadeInvalidaException();
		}

		if (fantasia.getParteBaixo().getCor().length() > 20 || fantasia.getParteBaixo().getCor().isEmpty()) {
			throw new CorInvalidaException();
		}

		if (fantasia.getParteBaixo().getPrecoVenda() < 1
				|| fantasia.getParteBaixo().getPrecoVenda() <= fantasia.getParteBaixo().getPrecoCompra()) {
			throw new PrecoInvalidoException();
		}

		if (fantasia.getParteBaixo().getPrecoCompra() < 1) {
			throw new PrecoInvalidoException();
		}

		if (fantasia.getParteBaixo().getTipo().length() > 20 || fantasia.getParteBaixo().getTipo().isEmpty()) {
			throw new TipoRoupaInvalidoException();
		}

		if (fantasia.getChapeu() != null) {
			if (fantasia.getChapeu().getCor().length() > 20 || fantasia.getParteBaixo().getCor().isEmpty()) {
				throw new CorInvalidaException();
			}

			if (fantasia.getChapeu().getPrecoVenda() < 1
					|| fantasia.getChapeu().getPrecoVenda() <= fantasia.getChapeu().getPrecoCompra()) {
				throw new PrecoInvalidoException();
			}

			if (fantasia.getChapeu().getPrecoCompra() < 1) {
				throw new PrecoInvalidoException();
			}

			if (fantasia.getChapeu().getNomeConjunto().length() > 20
					|| fantasia.getChapeu().getNomeConjunto().isEmpty()) {
				throw new NomeConjuntoInvalidoException();
			}

			boolean diferente = true;

			if (fantasia.getChapeu().getNomeConjunto().equalsIgnoreCase(fantasia.getParteBaixo().getNomeConjunto())) {
				diferente = false;
			}

			if (diferente) {
				throw new NomeConjuntoDiferentesException();
			}
		}

		if (fantasia.getParteCima() != null) {
			if (fantasia.getParteCima().getCor().length() > 20 || fantasia.getParteCima().getCor().isEmpty()) {
				throw new CorInvalidaException();
			}

			if (fantasia.getParteCima().getPrecoVenda() < 1
					|| fantasia.getParteCima().getPrecoVenda() <= fantasia.getParteCima().getPrecoCompra()) {
				throw new PrecoInvalidoException();
			}

			if (fantasia.getParteCima().getPrecoCompra() < 1) {
				throw new PrecoInvalidoException();
			}

			if (fantasia.getParteCima().getNomeConjunto().length() > 20
					|| fantasia.getParteCima().getNomeConjunto().isEmpty()) {
				throw new NomeConjuntoInvalidoException();
			}

			boolean diferente = true;

			if (fantasia.getParteCima().getNomeConjunto()
					.equalsIgnoreCase(fantasia.getParteBaixo().getNomeConjunto())) {
				diferente = false;
			}

			if (diferente) {
				throw new NomeConjuntoDiferentesException();
			}
		}

		repositorio.inserir(fantasia);
		valorCompras = valorCompras + (fantasia.getPrecoCompra() * fantasia.getQuantidade());
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
	public void atualizar(Fantasia fantasia) throws QuantidadeInvalidaException, CorInvalidaException,
			PrecoInvalidoException, TipoRoupaInvalidoException, NomeConjuntoInvalidoException,
			NomeConjuntoDiferentesException, ConjuntoInexistenteException, IOException {
		if (fantasia.getQuantidade() < 1 || fantasia.getQuantidade() > 1000) {
			throw new QuantidadeInvalidaException();
		}

		if (fantasia.getParteBaixo().getCor().length() > 20 || fantasia.getParteBaixo().getCor().isEmpty()) {
			throw new CorInvalidaException();
		}

		if (fantasia.getParteBaixo().getPrecoVenda() < 1
				|| fantasia.getParteBaixo().getPrecoVenda() < fantasia.getParteBaixo().getPrecoCompra()) {
			throw new PrecoInvalidoException();
		}

		if (fantasia.getParteBaixo().getPrecoCompra() < 1) {
			throw new PrecoInvalidoException();
		}

		if (fantasia.getParteBaixo().getTipo().length() > 20 || fantasia.getParteBaixo().getTipo().isEmpty()) {
			throw new TipoRoupaInvalidoException();
		}

		if (fantasia.getChapeu() != null) {
			if (fantasia.getChapeu().getCor().length() > 20 || fantasia.getParteBaixo().getCor().isEmpty()) {
				throw new CorInvalidaException();
			}

			if (fantasia.getChapeu().getPrecoVenda() < 1
					|| fantasia.getChapeu().getPrecoVenda() < fantasia.getChapeu().getPrecoCompra()) {
				throw new PrecoInvalidoException();
			}

			if (fantasia.getChapeu().getPrecoCompra() < 1) {
				throw new PrecoInvalidoException();
			}

			if (fantasia.getChapeu().getNomeConjunto().length() > 20
					|| fantasia.getChapeu().getNomeConjunto().isEmpty()) {
				throw new NomeConjuntoInvalidoException();
			}

			boolean diferente = true;

			if (fantasia.getChapeu().getNomeConjunto().equalsIgnoreCase(fantasia.getParteBaixo().getNomeConjunto())) {
				diferente = false;
			}

			if (diferente) {
				throw new NomeConjuntoDiferentesException();
			}
		}

		if (fantasia.getParteCima() != null) {
			if (fantasia.getParteCima().getCor().length() > 20 || fantasia.getParteCima().getCor().isEmpty()) {
				throw new CorInvalidaException();
			}

			if (fantasia.getParteCima().getPrecoVenda() < 1
					|| fantasia.getParteCima().getPrecoVenda() < fantasia.getParteCima().getPrecoCompra()) {
				throw new PrecoInvalidoException();
			}

			if (fantasia.getParteCima().getPrecoCompra() < 1) {
				throw new PrecoInvalidoException();
			}

			if (fantasia.getParteCima().getNomeConjunto().length() > 20
					|| fantasia.getParteCima().getNomeConjunto().isEmpty()) {
				throw new NomeConjuntoInvalidoException();
			}

			boolean diferente = true;

			if (fantasia.getParteCima().getNomeConjunto()
					.equalsIgnoreCase(fantasia.getParteBaixo().getNomeConjunto())) {
				diferente = false;
			}

			if (diferente) {
				throw new NomeConjuntoDiferentesException();
			}
		}

		Fantasia temp = repositorio.procurar(fantasia.getParteBaixo().getNomeConjunto());
		repositorio.atualizar(fantasia);
		valorCompras = valorCompras - (temp.getPrecoCompra() * temp.getQuantidade())
				+ (fantasia.getPrecoCompra() * fantasia.getQuantidade());
	}

	/**
	 * Procurar Fantasia
	 * 
	 * @param nomeConjunto
	 * @return
	 * @throws ConjuntoInexistenteException
	 * @throws NomeConjuntoInvalidoException
	 */
	public Fantasia procurar(String nomeConjunto) throws ConjuntoInexistenteException, NomeConjuntoInvalidoException {
		if (nomeConjunto == null || nomeConjunto.equals("") || nomeConjunto.equals(" ")) {
			throw new NomeConjuntoInvalidoException();
		}

		return repositorio.procurar(nomeConjunto);
	}

	/**
	 * Remover Fantasia
	 * 
	 * @param nomeConjunto
	 * @throws ConjuntoInexistenteException
	 * @throws IOException
	 * @throws NomeConjuntoInvalidoException
	 */
	public void remover(String nomeConjunto)
			throws ConjuntoInexistenteException, IOException, NomeConjuntoInvalidoException {
		if (nomeConjunto == null || nomeConjunto.equals("") || nomeConjunto.equals(" ")) {
			throw new NomeConjuntoInvalidoException();
		}

		Fantasia temp = repositorio.procurar(nomeConjunto);
		repositorio.remover(nomeConjunto);
		valorCompras = valorCompras - (temp.getPrecoCompra() * temp.getQuantidade());
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
		Fantasia fantasia = repositorio.procurar(nomeFantasia);

		if (vendidos <= 0 || vendidos > fantasia.getQuantidade()) {
			throw new QuantidadeInvalidaException();
		}

		int quantidade = fantasia.getQuantidade() - vendidos;
		fantasia.setQuantidade(quantidade);

		if (quantidade == 0) {
			valorVendas = valorVendas + (vendidos * fantasia.getPrecoVenda());
			repositorio.atualizar(fantasia);
			repositorio.remover(fantasia.getParteBaixo().getNomeConjunto());
		} else {
			valorVendas = valorVendas + (vendidos * fantasia.getPrecoVenda());
			repositorio.atualizar(fantasia);
		}
	}

	/**
	 * Comprar Fantasia
	 * 
	 * @param fantasia
	 * @param adquiridos
	 * @throws QuantidadeInvalidaException
	 * @throws ConjuntoInexistenteException
	 * @throws IOException
	 */
	public void aumentarEstoque(String nomeFantasia, int adquiridos)
			throws QuantidadeInvalidaException, ConjuntoInexistenteException, IOException {
		Fantasia fantasia = repositorio.procurar(nomeFantasia);

		if (adquiridos <= 0) {
			throw new QuantidadeInvalidaException();
		}

		int quantidade = fantasia.getQuantidade() + adquiridos;
		fantasia.setQuantidade(quantidade);
		valorCompras = valorCompras + (adquiridos * fantasia.getPrecoCompra());
		repositorio.atualizar(fantasia);
	}

	/**
	 * Gerar Receita
	 * 
	 * @return
	 */
	public String gerarReceita() {
		double lucro = valorVendas - valorCompras;
		return ("Ganhos: " + valorVendas + "\nDespesas: " + valorCompras + "\nLucro: " + lucro);
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