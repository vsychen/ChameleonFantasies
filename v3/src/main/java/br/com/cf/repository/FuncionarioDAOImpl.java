package br.com.cf.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cf.domain.Funcionario;

@Repository("FuncionarioDAO")
@Transactional
public class FuncionarioDAOImpl extends HibernateDAOImpl<Funcionario> implements FuncionarioDAO {

	public FuncionarioDAOImpl() {

	}

	public synchronized void salvar(Funcionario f) {
		Session s = getSession();
		s.save(f);
		s.flush();
	}

	public synchronized void atualizar(Funcionario f) {
		Session s = getSession();
		s.update(f);
		s.flush();
	}

	public synchronized void salvarOuAtualizar(Funcionario f) {
		Session s = getSession();
		s.saveOrUpdate(f);
		s.flush();
	}

	public synchronized void deletar(Funcionario f) {
		Session s = getSession();
		s.delete(f);
		s.flush();
	}

	public synchronized Funcionario procurar(String cpf) {
		Session s = getSession();
		List<?> l = s.createQuery("from Funcionario where cpf='" + cpf + "'").list();
		s.flush();
		return (l.size() > 0) ? (Funcionario) l.get(0) : new Funcionario("", cpf, "", "", "", "0");
	}

	public synchronized List<Funcionario> listar() {
		Session s = getSession();
		List<Funcionario> l = new ArrayList<Funcionario>();

		for (Object o : s.createQuery("from Funcionario").list())
			l.add((Funcionario) o);

		s.flush();
		return l;
	}
}