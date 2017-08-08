package dados.base.roupas;

public class Chapeu extends Roupa {

	/**
	 * @param nomeConjunto
	 * @param preco
	 * @param cor
	 * @param enfeite
	 * @param estampa
	 */
	public Chapeu(String nomeConjunto, double precoVenda, double precoCompra, String cor, boolean enfeite,
			boolean estampa) {
		super(nomeConjunto, precoVenda, precoCompra, cor, enfeite, estampa);
	}

	public String toString() {
		return ("Chapeu: \n" + super.toString());
	}
}