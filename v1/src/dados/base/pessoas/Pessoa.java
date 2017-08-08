package dados.base.pessoas;

public abstract class Pessoa {
	private String nome;
	private String cpf;
	private Endereco endereco;

	/**
	 * @param nome
	 * @param cpf
	 * @param endereco
	 */
	public Pessoa(String nome, String cpf, Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String toString() {
		return ("Nome: " + nome + "\nCpf: " + cpf + "\nEndereço: " + endereco.toString());
	}
}