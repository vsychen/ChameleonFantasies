package br.com.chameleonfantasies.model.exceptions;

public class InvalidFieldException extends Exception {
	private static final long serialVersionUID = -1723599612433190291L;

	public InvalidFieldException() {
		super("Um ou mais campos foram inseridos de forma inválida.");
	}
}