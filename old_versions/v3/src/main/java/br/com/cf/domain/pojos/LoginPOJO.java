package br.com.cf.domain.pojos;

public class LoginPOJO {
	private String cpf;
	private String senha;

	public LoginPOJO() {
		this.cpf = "";
		this.senha = "";
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}