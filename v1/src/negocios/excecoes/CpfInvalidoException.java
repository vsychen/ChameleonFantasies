package negocios.excecoes;

public class CpfInvalidoException extends Exception {
	private static final long serialVersionUID = -7586747553503446094L;

	public CpfInvalidoException() {
		super("O campo 'Cpf' não foi inserido de forma válida.");
	}
}