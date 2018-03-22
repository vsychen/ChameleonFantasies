package br.com.chameleonfantasies.model.exceptions;

public class InsufficientStockException extends Exception {
	private static final long serialVersionUID = 4026426926640615016L;

	public InsufficientStockException(int quantity, int stockQuantity) {
		super("Não há estoque suficiente. Quantidade desejada: " + quantity + ". Quantidade em estoque: "
				+ stockQuantity + ".");
	}
}