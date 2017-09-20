package br.com.cf.domain.pojos;

import java.math.BigDecimal;

public class ClientePOJO {
	private String nome;
	private String cpf;
	private String gastos;

	public ClientePOJO() {
		this.nome = "";
		this.cpf = "";
		this.gastos = "0";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getGastos() {
		return gastos;
	}

	public void setGastos(String gastos) {
		this.gastos = new BigDecimal(gastos).stripTrailingZeros().toPlainString();
	}
}