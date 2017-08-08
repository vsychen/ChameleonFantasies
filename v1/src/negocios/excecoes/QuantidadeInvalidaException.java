package negocios.excecoes;

public class QuantidadeInvalidaException extends Exception {
	private static final long serialVersionUID = 2367055732068987124L;

	public QuantidadeInvalidaException() {
		super("O campo 'Quantidade' não foi inserido de forma válida.");
	}
}