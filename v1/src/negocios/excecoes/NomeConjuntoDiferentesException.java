package negocios.excecoes;

public class NomeConjuntoDiferentesException extends Exception {
	private static final long serialVersionUID = -9124225125411183326L;

	public NomeConjuntoDiferentesException() {
		super("Os nomes das partes do conjunto não coincidem.");
	}
}