package negocios.excecoes;

public class TempoTrabalhoInvalidoException extends Exception {
	private static final long serialVersionUID = -8680101444971987600L;

	public TempoTrabalhoInvalidoException() {
		super("O campo 'Tempo de trabalho' n�o foi inserido de forma v�lida.");
	}
}