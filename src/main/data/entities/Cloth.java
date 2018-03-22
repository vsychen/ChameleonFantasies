package main.data.entities;

public class Cloth {
	private String type;
	private String color;
	private boolean ornate;
	private boolean stamped;

	public Cloth(String type, String color, boolean ornament, boolean stamp) {
		setType(type);
		setColor(color);
		setOrnament(ornament);
		setStamp(stamp);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isOrnate() {
		return ornate;
	}

	public void setOrnament(boolean ornament) {
		this.ornate = ornament;
	}

	public boolean isStamped() {
		return stamped;
	}

	public void setStamp(boolean stamp) {
		this.stamped = stamp;
	}

	public String toString() {
		return getType() + " " + getColor() + ". " + ((isOrnate()) ? "Com enfeites. " : "Sem enfeites. ")
				+ ((isStamped()) ? "Com estampas." : "Sem estampas.");
	}
}