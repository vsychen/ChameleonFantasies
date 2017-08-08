package main.business.exceptions;

public class LoginErrorException extends Exception {
	private static final long serialVersionUID = -2805029449872730658L;

	public LoginErrorException(String error) {
		super(error);
	}
}
