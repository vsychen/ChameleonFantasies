package br.com.cf.exceptions;

public class InexistentObjectException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InexistentObjectException(String objectName) {
		super("O objeto " + objectName + " escolhido não existe no banco de dados.");
	}
}