package negocios.excecoes;

public class TipoRoupaInvalidoException extends Exception {
	private static final long serialVersionUID = -8457463834123761846L;

	public TipoRoupaInvalidoException() {
		super("O campo 'Tipo da roupa' n�o foi inserido de forma v�lida.");
	}
}