package br.com.cf.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cf.domain.Fantasia;

@Repository("FantasiaDAO")
@Transactional
public class FantasiaDAOImpl extends HibernateDAOImpl<Fantasia> implements FantasiaDAO {

	public FantasiaDAOImpl() {

	}

	public void salvar(Fantasia f) {
		Session s = getSession();
		s.save(f);
		s.flush();
	}

	public void atualizar(Fantasia f) {
		Session s = getSession();
		s.update(f);
		s.flush();
	}

	public void salvarOuAtualizar(Fantasia f) {
		Session s = getSession();
		s.saveOrUpdate(f);
		s.flush();
	}

	public void deletar(Fantasia f) {
		Session s = getSession();
		s.delete(f);
		s.flush();
	}

	public Fantasia procurar(String codigo) {
		Session s = getSession();
		List<?> l = s.createQuery("from Fantasia where codigo='" + codigo + "'").list();
		s.flush();
		return (l.size() > 0) ? (Fantasia) l.get(0) : new Fantasia("", codigo, 0, "0", "0");
	}

	public List<Fantasia> listar() {
		Session s = getSession();
		List<Fantasia> l = new ArrayList<Fantasia>();

		for (Object o : s.createQuery("from Fantasia").list())
			l.add((Fantasia) o);

		s.flush();
		return l;
	}
}