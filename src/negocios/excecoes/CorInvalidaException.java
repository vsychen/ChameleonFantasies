package negocios.excecoes;

public class CorInvalidaException extends Exception {
	private static final long serialVersionUID = -4409085772761893073L;

	public CorInvalidaException() {
		super("O campo 'Cor' n�o foi inserido de forma v�lida.");
	}
}