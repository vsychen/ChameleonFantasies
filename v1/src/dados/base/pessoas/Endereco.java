package dados.base.pessoas;

public class Endereco {
	private String rua;
	private int numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;

	/**
	 * Endereço com número e complemento
	 * 
	 * @param rua
	 * @param numero
	 * @param complemento
	 * @param bairro
	 * @param cidade
	 * @param estado
	 */
	public Endereco(String rua, int numero, String complemento, String bairro, String cidade, String estado) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	/**
	 * Endereço com número, mas sem complemento
	 * 
	 * @param rua
	 * @param numero
	 * @param bairro
	 * @param cidade
	 * @param estado
	 */
	public Endereco(String rua, int numero, String bairro, String cidade, String estado) {
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	/**
	 * Endereço com complemento, mas sem número
	 * 
	 * @param rua
	 * @param complemento
	 * @param bairro
	 * @param cidade
	 * @param estado
	 */
	public Endereco(String rua, String complemento, String bairro, String cidade, String estado) {
		this.rua = rua;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	/**
	 * Endereço sem número e sem complemento
	 * 
	 * @param rua
	 * @param bairro
	 * @param cidade
	 * @param estado
	 */
	public Endereco(String rua, String bairro, String cidade, String estado) {
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String toString() {
		String retorno = "";

		if (numero == 0 && complemento == null) {
			retorno = (rua + " S/N, " + bairro + " - " + cidade + " - " + estado + "\n");
		} else if (numero == 0 && complemento != null) {
			retorno = (rua + " S/N - COMPLEMENTO: " + complemento + ", " + bairro + " - " + cidade + " - " + estado
					+ "\n");
		} else if (numero != 0 && complemento == null) {
			retorno = (rua + " Nº " + numero + ", " + bairro + " - " + cidade + " - " + estado + "\n");
		} else if (numero != 0 && complemento != null) {
			retorno = (rua + " Nº " + numero + "/" + complemento + ", " + bairro + " - " + cidade + " - " + estado
					+ "\n");
		}

		return retorno;
	}
}