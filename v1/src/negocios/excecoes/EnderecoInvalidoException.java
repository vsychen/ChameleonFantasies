package negocios.excecoes;

public class EnderecoInvalidoException extends Exception {
	private static final long serialVersionUID = -8652172113449225842L;

	public EnderecoInvalidoException(String string) {
		super(string);
	}
}