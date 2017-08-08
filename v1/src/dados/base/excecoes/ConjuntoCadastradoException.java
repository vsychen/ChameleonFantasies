package dados.base.excecoes;

public class ConjuntoCadastradoException extends Exception {
	private static final long serialVersionUID = -3351820584597504117L;

	public ConjuntoCadastradoException() {
		super("Conjunto já cadastrado.");
	}
}