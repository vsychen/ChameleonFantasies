package negocios.excecoes;

public class TempoTrabalhoInvalidoException extends Exception {
	private static final long serialVersionUID = -8680101444971987600L;

	public TempoTrabalhoInvalidoException() {
		super("O campo 'Tempo de trabalho' não foi inserido de forma válida.");
	}
}