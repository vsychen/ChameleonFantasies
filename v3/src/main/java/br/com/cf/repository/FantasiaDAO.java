package br.com.cf.repository;

import java.util.List;

import br.com.cf.domain.Fantasia;

public interface FantasiaDAO extends HibernateDAO<Fantasia> {

	public void salvar(Fantasia f);

	public void atualizar(Fantasia f);

	public void salvarOuAtualizar(Fantasia f);

	public void deletar(Fantasia f);

	public Fantasia procurar(String codigo);

	public List<Fantasia> listar();

}