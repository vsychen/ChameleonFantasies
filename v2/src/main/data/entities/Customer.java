package main.data.entities;

public class Customer {
	private String name;
	private String cpf;
	private double spending;

	public Customer(String name, String cpf) {
		setName(name);
		setCpf(cpf);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	private void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getSpending() {
		return spending;
	}

	public void setSpending(double spending) {
		this.spending = spending;
	}

	public String toString() {
		return "Nome: " + getName() + "\nGastos: " + getSpending() + "\n";
	}
}