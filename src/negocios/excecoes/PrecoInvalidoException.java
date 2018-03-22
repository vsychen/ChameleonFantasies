package negocios.excecoes;

public class PrecoInvalidoException extends Exception {
	private static final long serialVersionUID = -6961682807544971933L;

	public PrecoInvalidoException() {
		super("O campo 'Preço' não foi inserido de forma válida.");
	}
}