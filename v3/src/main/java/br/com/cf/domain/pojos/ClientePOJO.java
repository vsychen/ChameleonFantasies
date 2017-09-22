package br.com.cf.domain.pojos;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

public class ClientePOJO {
	@Size(min = 5, max = 100)
	private String nome;
	@CPF
	private String cpf;
	@Min(0)
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