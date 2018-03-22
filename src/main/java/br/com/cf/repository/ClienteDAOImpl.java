package br.com.cf.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cf.domain.Cliente;

@Repository("ClienteDAO")
@Transactional
public class ClienteDAOImpl extends HibernateDAOImpl<Cliente> implements ClienteDAO {

	public ClienteDAOImpl() {

	}

	public synchronized void salvar(Cliente c) {
		Session s = getSession();
		s.save(c);
		s.flush();
	}

	public synchronized void atualizar(Cliente c) {
		Session s = getSession();
		s.update(c);
		s.flush();
	}

	public synchronized void salvarOuAtualizar(Cliente c) {
		Session s = getSession();
		s.saveOrUpdate(c);
		s.flush();
	}

	public synchronized void deletar(Cliente c) {
		Session s = getSession();
		s.delete(c);
		s.flush();
	}

	public synchronized Cliente procurar(String cpf) {
		Session s = getSession();
		List<?> l = s.createQuery("from Cliente where cpf='" + cpf + "'").list();
		s.flush();
		return (l.size() > 0) ? (Cliente) l.get(0) : new Cliente("", cpf, "0");
	}

	public synchronized List<Cliente> listar() {
		Session s = getSession();
		List<Cliente> l = new ArrayList<Cliente>();

		for (Object o : s.createQuery("from Cliente").list())
			l.add((Cliente) o);

		s.flush();
		return l;
	}
}