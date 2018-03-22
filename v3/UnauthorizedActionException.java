package br.com.chameleonfantasies.model.exceptions;

public class UnauthorizedActionException extends Exception {
	private static final long serialVersionUID = -8865447065416437063L;

	public UnauthorizedActionException() {
		super("A conta logada não possui permissão para realizar esta ação.");
	}
}