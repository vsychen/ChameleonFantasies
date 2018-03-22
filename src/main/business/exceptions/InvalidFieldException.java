package main.business.exceptions;

public class InvalidFieldException extends Exception {
	private static final long serialVersionUID = -1866383730654755965L;

	public InvalidFieldException(String error) {
		super(error);
	}
}