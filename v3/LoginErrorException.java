package br.com.chameleonfantasies.model.exceptions;

public class LoginErrorException extends Exception {
	private static final long serialVersionUID = 2090262846174904455L;

	public LoginErrorException(String error) {
		super(error);
	}
}