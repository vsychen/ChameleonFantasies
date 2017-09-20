package br.com.cf.service;

import java.util.List;

public interface CustomService<E> {

	public void salvar(E pojo);

	public void atualizar(E pojo);

	public void salvarOuAtualizar(E pojo);

	public E procurar(String s);

	public void remover(String s);

	public List<E> listar();

}