package main.business.exceptions;

public class NotFirstAccountException extends Exception {
	private static final long serialVersionUID = 3952171235014519182L;

	public NotFirstAccountException() {
		super("Já há pelo menos uma conta cadastrada no sistema.");
	}
}
