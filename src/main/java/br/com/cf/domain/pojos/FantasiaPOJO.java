package br.com.cf.domain.pojos;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import br.com.cf.constraints.Codigo;

public class FantasiaPOJO {
	@Size(min = 2, max = 100)
	private String nome;
	@Codigo
	private String codigo;
	@Min(0)
	private int quantidade;
	@Min(1)
	private String precoCompra;
	@Min(2)
	private String precoVenda;
	@Size(max = 200)
	private String descChapeu;
	@Size(max = 200)
	private String descParteCima;
	@Size(max = 200)
	private String descParteBaixo;
	@Size(max = 200)
	private String descBracos;
	@Size(max = 200)
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
		this.precoCompra = new BigDecimal(precoCompra).stripTrailingZeros().toPlainString();
	}

	public String getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(String precoVenda) {
		this.precoVenda = new BigDecimal(precoVenda).stripTrailingZeros().toPlainString();
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