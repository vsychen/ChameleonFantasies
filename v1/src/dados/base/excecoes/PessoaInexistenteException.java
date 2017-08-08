package dados.base.excecoes;

public class PessoaInexistenteException extends Exception {
	private static final long serialVersionUID = 5109070768516624957L;

	public PessoaInexistenteException() {
		super("Cpf não encontrado.");
	}
}