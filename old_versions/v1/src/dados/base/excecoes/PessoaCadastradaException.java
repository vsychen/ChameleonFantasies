package dados.base.excecoes;

public class PessoaCadastradaException extends Exception {
	private static final long serialVersionUID = -6117041733585760810L;

	public PessoaCadastradaException() {
		super("Cpf já cadastrado.");
	}
}