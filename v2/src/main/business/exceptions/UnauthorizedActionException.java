package main.business.exceptions;

public class UnauthorizedActionException extends Exception {
	private static final long serialVersionUID = 2065311591861847970L;

	public UnauthorizedActionException() {
		super("A conta logada não possui permissão para realizar esta ação.");
	}
}