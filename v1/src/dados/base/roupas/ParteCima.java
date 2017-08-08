package dados.base.roupas;

public class ParteCima extends Roupa {
	private String tipo;

	/**
	 * @param nomeConjunto
	 * @param preco
	 * @param cor
	 * @param enfeite
	 * @param estampa
	 * @param tipo
	 */
	public ParteCima(String nomeConjunto, double precoVenda, double precoCompra, String cor, boolean enfeite,
			boolean estampa, String tipo) {
		super(nomeConjunto, precoVenda, precoCompra, cor, enfeite, estampa);
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toString() {
		return ("Parte de cima: \nTipo: " + tipo + "\n" + super.toString());
	}
}