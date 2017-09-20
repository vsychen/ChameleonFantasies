package br.com.cf.domain.pojos;

public class FantasiaPOJO {
	private String nome;
	private String codigo;
	private int quantidade;
	private String precoCompra;
	private String precoVenda;
	private String descChapeu;
	private String descParteCima;
	private String descParteBaixo;
	private String descBracos;
	private String descSapatos;

	public FantasiaPOJO() {
		this.nome = "";
		this.codigo = "";
		this.quantidade = 0;
		this.precoCompra = "0";
		this.precoVenda = "0";
		this.descChapeu = "";
		this.descParteCima = "";
		this.descParteBaixo = "";
		this.descBracos = "";
		this.descSapatos = "";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(String precoCompra) {
		this.precoCompra = precoCompra;
	}

	public String getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(String precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getDescChapeu() {
		return descChapeu;
	}

	public void setDescChapeu(String descChapeu) {
		this.descChapeu = descChapeu;
	}

	public String getDescParteCima() {
		return descParteCima;
	}

	public void setDescParteCima(String descParteCima) {
		this.descParteCima = descParteCima;
	}

	public String getDescParteBaixo() {
		return descParteBaixo;
	}

	public void setDescParteBaixo(String descParteBaixo) {
		this.descParteBaixo = descParteBaixo;
	}

	public String getDescBracos() {
		return descBracos;
	}

	public void setDescBracos(String descBracos) {
		this.descBracos = descBracos;
	}

	public String getDescSapatos() {
		return descSapatos;
	}

	public void setDescSapatos(String descSapatos) {
		this.descSapatos = descSapatos;
	}
}