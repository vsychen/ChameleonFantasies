package main.business.exceptions;

public class InsufficientStockException extends Exception {
	private static final long serialVersionUID = -3480734463423646389L;

	public InsufficientStockException(int quantity, int stockQuantity) {
		super("Não há estoque suficiente. Quantidade desejada: " + quantity + ". Quantidade em estoque: "
				+ stockQuantity + ".");
	}
}