package br.com.chameleonfantasies.model.exceptions;

public class NotFirstAccountException extends Exception {
	private static final long serialVersionUID = -8255387495515883403L;

	public NotFirstAccountException() {
		super("Já há pelo menos uma conta cadastrada no sistema.");
	}
}