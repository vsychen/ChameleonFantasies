package negocios.excecoes;

public class QuantidadeInvalidaException extends Exception {
	private static final long serialVersionUID = 2367055732068987124L;

	public QuantidadeInvalidaException() {
		super("O campo 'Quantidade' n�o foi inserido de forma v�lida.");
	}
}