package negocios.excecoes;

public class TxtInvalidoException extends Exception {
	private static final long serialVersionUID = 2460236568285687591L;

	public TxtInvalidoException() {
		super("O arquivo .txt n�o est� configurado corretamente.");
	}
}