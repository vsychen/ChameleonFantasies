package main.data.entities;

import java.util.Random;

public class Employee {
	private final char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	private String name;
	private String cpf;
	private String password;
	private String email;
	private Address address;
	private String job;
	private double salary;

	public Employee(String name, String cpf, String email, Address address, String job, double salary) {
		setName(name);
		setCpf(cpf);
		setPassword(generatePassword());
		setEmail(email);
		setAddress(address);
		setJob(job);
		setSalary(salary);
	}

	public Employee(String name, String cpf, String password, String email, Address address, String job,
			double salary) {
		setName(name);
		setCpf(cpf);
		setPassword(password);
		setEmail(email);
		setAddress(address);
		setJob(job);
		setSalary(salary);
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

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	private String generatePassword() {
		Random r = new Random();
		char[] s = new char[4];

		s[0] = CHARS[r.nextInt(35)];
		s[1] = CHARS[r.nextInt(35)];
		s[2] = CHARS[r.nextInt(35)];
		s[3] = CHARS[r.nextInt(35)];

		return String.valueOf(s);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String toString() {
		return "Nome: " + getName() + "\nE-mail: " + getEmail() + "\nEndereço: " + getAddress().toString() + "\nCargo: "
				+ getJob() + "\nSalário: " + getSalary() + "\n";
	}
}