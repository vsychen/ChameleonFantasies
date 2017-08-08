package main.data.entities;

public class Fantasy {
	private String name;
	private String id;
	private Cloth hat;
	private Cloth top;
	private Cloth bottom;
	private Cloth arms;
	private Cloth shoes;
	private int quantity;
	private double buyPrice;
	private double sellPrice;

	public Fantasy(String name, String id, Cloth hat, Cloth top, Cloth bottom, Cloth arms, Cloth shoes, int quantity,
			double buyPrice, double sellPrice) {
		setName(name);
		setId(id);
		setHat(hat);
		setTop(top);
		setBottom(bottom);
		setArms(arms);
		setShoes(shoes);
		setQuantity(quantity);
		setBuyPrice(buyPrice);
		setSellPrice(sellPrice);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public Cloth getHat() {
		return hat;
	}

	public void setHat(Cloth hat) {
		this.hat = hat;
	}

	public Cloth getTop() {
		return top;
	}

	public void setTop(Cloth top) {
		this.top = top;
	}

	public Cloth getBottom() {
		return bottom;
	}

	public void setBottom(Cloth bottom) {
		this.bottom = bottom;
	}

	public Cloth getArms() {
		return arms;
	}

	public void setArms(Cloth arms) {
		this.arms = arms;
	}

	public Cloth getShoes() {
		return shoes;
	}

	public void setShoes(Cloth shoes) {
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

	public String toString() {
		return "Nome: " + getName() + ((getHat() != null) ? "\nChapeu: " + getHat().toString() : "")
				+ ((getTop() != null) ? "\nParte de cima: " + getTop().toString() : "")
				+ ((getBottom() != null) ? "\nParte de baixo: " + getBottom().toString() : "")
				+ ((getArms() != null) ? "\nBraços: " + getArms().toString() : "")
				+ ((getShoes() != null) ? "\nSapatos: " + getShoes().toString() : "") + "\nQuantidade: " + getQuantity()
				+ "\nPreço de compra da fantasia: " + getBuyPrice() + "\nPreço de venda da fantasia: " + getSellPrice()
				+ "\n";
	}
}