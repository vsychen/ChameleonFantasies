package negocios.excecoes;

public class TipoRoupaInvalidoException extends Exception {
	private static final long serialVersionUID = -8457463834123761846L;

	public TipoRoupaInvalidoException() {
		super("O campo 'Tipo da roupa' não foi inserido de forma válida.");
	}
}