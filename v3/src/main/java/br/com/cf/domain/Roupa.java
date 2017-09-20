package br.com.cf.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roupa")
public class Roupa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "roupa_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fantasia_id", nullable = false)
	private Fantasia fantasia;

	@Column(name = "tipo", nullable = false, length = 1)
	private char tipo;

	@Column(name = "descricao", nullable = false, length = 200)
	private String descricao;

	Roupa() {

	}

	public Roupa(Fantasia fantasia, char tipo, String descricao) {
		super();
		this.fantasia = fantasia;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public Fantasia getFantasia() {
		return fantasia;
	}

	public void setFantasia(Fantasia fantasia) {
		this.fantasia = fantasia;
	}

	public char getTipo() {
		return tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}