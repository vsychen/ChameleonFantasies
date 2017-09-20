package br.com.cf.repository;

import java.util.List;

import br.com.cf.domain.Cliente;

public interface ClienteDAO extends HibernateDAO<Cliente> {

	public void salvar(Cliente c);

	public void atualizar(Cliente c);

	public void salvarOuAtualizar(Cliente c);

	public void deletar(Cliente c);

	public Cliente procurar(String cpf);

	public List<Cliente> listar();

}