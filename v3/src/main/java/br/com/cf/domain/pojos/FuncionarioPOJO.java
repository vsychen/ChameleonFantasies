package br.com.cf.domain.pojos;

public class FuncionarioPOJO {
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private String cargo;
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
		this.salario = salario;
	}
}