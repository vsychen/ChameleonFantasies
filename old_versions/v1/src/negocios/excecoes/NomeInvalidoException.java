package negocios.excecoes;

public class NomeInvalidoException extends Exception {
	private static final long serialVersionUID = 4415370617987614181L;

	public NomeInvalidoException() {
		super("O campo 'Nome' n�o foi inserido de forma v�lida.");
	}
}