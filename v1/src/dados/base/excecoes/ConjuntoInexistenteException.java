package dados.base.excecoes;

public class ConjuntoInexistenteException extends Exception {
	private static final long serialVersionUID = 7877996844747221706L;

	public ConjuntoInexistenteException() {
		super("Conjunto não encontrado.");
	}
}