package dados.base.roupas;

public abstract class Roupa {
	private String nomeConjunto;
	private double precoVenda;
	private double precoCompra;
	private String cor;
	private boolean enfeite;
	private boolean estampa;

	/**
	 * @param nomeConjunto
	 * @param preco
	 * @param cor
	 * @param enfeite
	 * @param estampa
	 */
	public Roupa(String nomeConjunto, double precoVenda, double precoCompra, String cor, boolean enfeite,
			boolean estampa) {
		this.nomeConjunto = nomeConjunto;
		this.precoVenda = precoVenda;
		this.precoCompra = precoCompra;
		this.cor = cor;
		this.enfeite = enfeite;
		this.estampa = estampa;
	}

	public String getNomeConjunto() {
		return nomeConjunto;
	}

	public void setNomeConjunto(String nomeConjunto) {
		this.nomeConjunto = nomeConjunto;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public boolean isEnfeite() {
		return enfeite;
	}

	public void setEnfeite(boolean enfeite) {
		this.enfeite = enfeite;
	}

	public boolean isEstampa() {
		return estampa;
	}

	public void setEstampa(boolean estampa) {
		this.estampa = estampa;
	}

	public String toString() {
		String retorno = "";

		if (enfeite && estampa) {
			retorno = ("Preço: " + precoVenda + "\nCor: " + cor
					+ "\nEnfeite/Estampa: Possui tanto enfeite quanto estampa. \n");
		} else if (enfeite && !estampa) {
			retorno = ("Preço: " + precoVenda + "\nCor: " + cor
					+ "\nEnfeite/Estampa: Possui enfeite e não possui estampa. \n");
		} else if (!enfeite && estampa) {
			retorno = ("Preço: " + precoVenda + "\nCor: " + cor
					+ "\nEnfeite/Estampa: Não possui enfeite e possui estampa. \n");
		} else if (!enfeite && !estampa) {
			retorno = ("Preço: " + precoVenda + "\nCor: " + cor
					+ "\nEnfeite/Estampa: Não possui nem enfeite nem estampa. \n");
		}

		return retorno;
	}
}