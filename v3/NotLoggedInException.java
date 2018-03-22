package br.com.chameleonfantasies.model.exceptions;

public class NotLoggedInException extends Exception {
	private static final long serialVersionUID = -2343431992541228655L;

	public NotLoggedInException() {
		super("Não há conta logada no sistema.");
	}
}