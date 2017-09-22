package br.com.cf.domain.pojos;

import java.math.BigDecimal;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.com.cf.constraints.Cargo;
import br.com.cf.constraints.Email;
import br.com.cf.constraints.Salario;
import br.com.cf.constraints.Telefone;

public class FuncionarioPOJO {
	@Size(min = 5, max = 100)
	private String nome;
	@CPF
	private String cpf;
	@Email
	private String email;
	@Telefone
	private String telefone;
	@Cargo
	private String cargo;
	@Salario
	private String salario;

	public FuncionarioPOJO() {
		this.nome = "";
		this.cpf = "";
		this.email = "";
		this.telefone = "";
		this.cargo = "";
		this.salario = "0";
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = new BigDecimal(salario).stripTrailingZeros().toPlainString();
	}
}