package main.data.exceptions;

public class DatabaseErrorException extends Exception {
	private static final long serialVersionUID = 8352609323287352557L;

	public DatabaseErrorException(String error) {
		super(error);
	}
}