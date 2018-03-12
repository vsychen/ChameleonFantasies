package dados.base.pessoas;

public class Funcionario extends Pessoa {
	private int tempoAnosTrabalho;

	/**
	 * @param nome
	 * @param cpf
	 * @param endereco
	 * @param tempoAnosTrabalho
	 */
	public Funcionario(String nome, String cpf, Endereco endereco, int tempoAnosTrabalho) {
		super(nome, cpf, endereco);
		this.tempoAnosTrabalho = tempoAnosTrabalho;
	}

	public int getTempoAnosTrabalho() {
		return tempoAnosTrabalho;
	}

	public void setTempoAnosTrabalho(int tempoAnosTrabalho) {
		this.tempoAnosTrabalho = tempoAnosTrabalho;
	}

	public String toString() {
		return ("Funcionário: \n" + super.toString() + "Tempo de trabalho (anos): " + tempoAnosTrabalho + "\n");
	}
}