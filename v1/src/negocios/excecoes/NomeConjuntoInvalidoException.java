package negocios.excecoes;

public class NomeConjuntoInvalidoException extends Exception {
	private static final long serialVersionUID = -2242732999917583250L;

	public NomeConjuntoInvalidoException() {
		super("O campo 'Nome do conjunto' não foi inserido de forma válida.");
	}
}