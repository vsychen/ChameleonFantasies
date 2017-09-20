package br.com.cf.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fantasia")
public class Fantasia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "fantasia_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "codigo", unique = true, nullable = false, length = 10)
	private String codigo;

	@Column(name = "quantidade", nullable = false)
	private int quantidade;

	@Column(name = "preco_compra", nullable = false, precision = 20, scale = 4)
	private String precoCompra;

	@Column(name = "preco_venda", nullable = false, precision = 20, scale = 4)
	private String precoVenda;

	@OneToMany(mappedBy = "fantasia", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Roupa> partes;

	Fantasia() {

	}

	public Fantasia(String nome, String codigo, int quantidade, String precoCompra, String precoVenda,
			List<Roupa> partes) {
		super();
		this.nome = nome;
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.precoCompra = precoCompra;
		this.precoVenda = precoVenda;
		this.partes = partes;
	}

	public Fantasia(String nome, String codigo, int quantidade, String precoCompra, String precoVenda, Roupa chapeu,
			Roupa parteCima, Roupa parteBaixo, Roupa bracos, Roupa sapatos) {
		super();
		this.nome = nome;
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.precoCompra = precoCompra;
		this.precoVenda = precoVenda;

		List<Roupa> partes = new ArrayList<Roupa>();
		partes.add(0, (chapeu != null) ? chapeu : new Roupa(this, 'h', ""));
		partes.add(1, (parteCima != null) ? parteCima : new Roupa(this, 't', ""));
		partes.add(2, (parteBaixo != null) ? parteBaixo : new Roupa(this, 'b', ""));
		partes.add(3, (bracos != null) ? bracos : new Roupa(this, 'a', ""));
		partes.add(4, (sapatos != null) ? sapatos : new Roupa(this, 's', ""));
		this.partes = partes;
	}

	public Long getId() {
		return id;
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

	public List<Roupa> getPartes() {
		return partes;
	}

	public void setPartes(List<Roupa> partes) {
		this.partes = partes;
	}

	public void setPartes(Roupa chapeu, Roupa parteCima, Roupa parteBaixo, Roupa bracos, Roupa sapatos) {
		this.partes.set(0, (chapeu != null) ? chapeu : new Roupa(this, 'h', ""));
		this.partes.set(1, (parteCima != null) ? parteCima : new Roupa(this, 't', ""));
		this.partes.set(2, (parteBaixo != null) ? parteBaixo : new Roupa(this, 'b', ""));
		this.partes.set(3, (bracos != null) ? bracos : new Roupa(this, 'a', ""));
		this.partes.set(4, (sapatos != null) ? sapatos : new Roupa(this, 's', ""));
	}
}