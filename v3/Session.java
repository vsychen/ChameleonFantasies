package br.com.chameleonfantasies.model.entities;

import java.time.LocalDateTime;

public class Session {
	private String cpf;
	private String password;
	private String job;
	private LocalDateTime dateLogin;

	public Session(String cpf, String password, String job) {
		setCpf(cpf);
		setPassword(password);
		setJob(job);
		setDateLogin(LocalDateTime.now());
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

	public String getJob() {
		return job;
	}

	private void setJob(String job) {
		this.job = job;
	}

	public LocalDateTime getDateLogin() {
		return dateLogin;
	}

	private void setDateLogin(LocalDateTime dateLogin) {
		this.dateLogin = dateLogin;
	}

	public String toString() {
		return "Cpf: " + getCpf() + "\nCargo: " + getJob() + "\nHora do login: " + getDateLogin() + "\n";
	}
}