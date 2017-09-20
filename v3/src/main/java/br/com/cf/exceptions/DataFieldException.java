package br.com.cf.exceptions;

public class DataFieldException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataFieldException() {
		super("Algu[m/ns] campo(s) fo[i/ram] informado(s) incorretamente.");
	}
}