package br.com.cf.repository;

import java.util.List;

import br.com.cf.domain.Funcionario;

public interface FuncionarioDAO extends HibernateDAO<Funcionario> {

	public void salvar(Funcionario f);

	public void atualizar(Funcionario f);

	public void salvarOuAtualizar(Funcionario f);

	public void deletar(Funcionario f);

	public Funcionario procurar(String cpf);

	public List<Funcionario> listar();

}