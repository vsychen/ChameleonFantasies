package dados.base.roupas;

public class Fantasia {
	private Chapeu chapeu;
	private ParteCima parteCima;
	private ParteBaixo parteBaixo;
	private int quantidade;

	/**
	 * Fantasia com chapéu e parte de cima
	 * 
	 * @param chapeu
	 * @param parteCima
	 * @param parteBaixo
	 * @param quantidade
	 */
	public Fantasia(Chapeu chapeu, ParteCima parteCima, ParteBaixo parteBaixo, int quantidade) {
		this.chapeu = chapeu;
		this.parteCima = parteCima;
		this.parteBaixo = parteBaixo;
		this.quantidade = quantidade;
	}

	/**
	 * Fantasia com parte de cima e sem chapéu
	 * 
	 * @param parteCima
	 * @param parteBaixo
	 * @param quantidade
	 */
	public Fantasia(ParteCima parteCima, ParteBaixo parteBaixo, int quantidade) {
		this.parteCima = parteCima;
		this.parteBaixo = parteBaixo;
		this.quantidade = quantidade;
	}

	/**
	 * Fantasia com chapéu e sem parte de cima
	 * 
	 * @param chapeu
	 * @param parteBaixo
	 * @param quantidade
	 */
	public Fantasia(Chapeu chapeu, ParteBaixo parteBaixo, int quantidade) {
		this.chapeu = chapeu;
		this.parteBaixo = parteBaixo;
		this.quantidade = quantidade;
	}

	/**
	 * Fantasia sem chapéu e sem parte de cima
	 * 
	 * @param parteBaixo
	 * @param quantidade
	 */
	public Fantasia(ParteBaixo parteBaixo, int quantidade) {
		this.parteBaixo = parteBaixo;
		this.quantidade = quantidade;
	}

	public Chapeu getChapeu() {
		return chapeu;
	}

	public void setChapeu(Chapeu chapeu) {
		this.chapeu = chapeu;
	}

	public ParteBaixo getParteBaixo() {
		return parteBaixo;
	}

	public void setParteBaixo(ParteBaixo parteBaixo) {
		this.parteBaixo = parteBaixo;
	}

	public ParteCima getParteCima() {
		return parteCima;
	}

	public void setParteCima(ParteCima parteCima) {
		this.parteCima = parteCima;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoVenda() {
		double retorno = 0;

		if (chapeu != null) {
			retorno = retorno + chapeu.getPrecoVenda();
		}

		if (parteCima != null) {
			retorno = retorno + parteCima.getPrecoVenda();
		}

		retorno = retorno + parteBaixo.getPrecoVenda();
		return retorno;
	}

	public double getPrecoCompra() {
		double retorno = 0;

		if (chapeu != null) {
			retorno = retorno + chapeu.getPrecoCompra();
		}

		if (parteCima != null) {
			retorno = retorno + parteCima.getPrecoCompra();
		}

		retorno = retorno + parteBaixo.getPrecoCompra();
		return retorno;
	}

	public String toString() {
		String retorno = "Fantasia " + parteBaixo.getNomeConjunto() + "\n";

		if (chapeu != null) {
			retorno = (retorno + chapeu.toString());
		}

		if (parteCima != null) {
			retorno = (retorno + parteCima.toString());
		}

		retorno = (retorno + parteBaixo.toString());
		return retorno;
	}
}