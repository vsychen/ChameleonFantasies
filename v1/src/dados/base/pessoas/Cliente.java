package dados.base.pessoas;

public class Cliente extends Pessoa {

	/**
	 * @param nome
	 * @param cpf
	 * @param endereco
	 */
	public Cliente(String nome, String cpf, Endereco endereco) {
		super(nome, cpf, endereco);
	}

	public String toString() {
		return ("Cliente: \n" + super.toString());
	}
}