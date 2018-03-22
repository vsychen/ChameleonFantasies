package br.com.chameleonfantasies.model.entities;

import java.io.Serializable;

public class Fantasy implements Serializable {
	private static final long serialVersionUID = 2868507150973946778L;

	private Long id;
	private String name;
	private Cloth hat;
	private Cloth top;
	private Cloth bottom;
	private Cloth arms;
	private Cloth shoes;
	private int quantity;
	private double buyPrice;
	private double sellPrice;

	public Fantasy(String name, Cloth hat, Cloth top, Cloth bottom, Cloth arms, Cloth shoes, int quantity,
			double buyPrice, double sellPrice) {
		Cloth aux = new Cloth("", "", false, false);

		setName(name);
		setQuantity(quantity);
		setBuyPrice(buyPrice);
		setSellPrice(sellPrice);

		setHat((hat == null) ? aux : hat);
		setTop((top == null) ? aux : top);
		setBottom((bottom == null) ? aux : bottom);
		setArms((arms == null) ? aux : arms);
		setShoes((shoes == null) ? aux : shoes);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cloth getHat() {
		return hat;
	}

	private void setHat(Cloth hat) {
		this.hat = hat;
	}

	public Cloth getTop() {
		return top;
	}

	private void setTop(Cloth top) {
		this.top = top;
	}

	public Cloth getBottom() {
		return bottom;
	}

	private void setBottom(Cloth bottom) {
		this.bottom = bottom;
	}

	public Cloth getArms() {
		return arms;
	}

	private void setArms(Cloth arms) {
		this.arms = arms;
	}

	public Cloth getShoes() {
		return shoes;
	}

	private void setShoes(Cloth shoes) {
		this.shoes = shoes;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	@Override
	public String toString() {
		return "Nome: " + getName()
				+ ((getHat().getType().equals("")) ? "\nSem chapéu." : "\nChapéu: " + getHat().toString())
				+ ((getTop().getType().equals("")) ? "\nSem parte de cima." : "\nParte de cima: " + getTop().toString())
				+ ((getBottom().getType().equals("")) ? "\nSem parte de baixo."
						: "\nParte de baixo: " + getBottom().toString())
				+ ((getArms().getType().equals("")) ? "\nSem braços." : "\nBraços: " + getArms().toString())
				+ ((getShoes().getType().equals("")) ? "\nSem sapatos." : "\nSapatos: " + getShoes().toString())
				+ "\nQuantidade: " + getQuantity() + "\nPreço de compra da fantasia: " + getBuyPrice()
				+ "\nPreço de venda da fantasia: " + getSellPrice() + "\n";
	}
}