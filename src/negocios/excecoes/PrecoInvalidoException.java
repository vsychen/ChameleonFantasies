package negocios.excecoes;

public class PrecoInvalidoException extends Exception {
	private static final long serialVersionUID = -6961682807544971933L;

	public PrecoInvalidoException() {
		super("O campo 'Pre�o' n�o foi inserido de forma v�lida.");
	}
}