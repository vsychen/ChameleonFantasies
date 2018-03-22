package br.com.chameleonfantasies.model.entities;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 3979388121406657272L;

	private Long id;
	private String name;
	private String cpf;
	private double spending;

	public Customer(String name, String cpf, double spending) {
		this.setName(name);
		this.setCpf(cpf);
		this.setSpending(spending);
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

	@Override
	public String toString() {
		return "Nome: " + getName() + "\nGastos: " + getSpending() + "\n";
	}
}