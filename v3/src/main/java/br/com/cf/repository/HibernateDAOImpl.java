package br.com.cf.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateDAOImpl<T> implements HibernateDAO<T> {
	@Autowired
	private SessionFactory sessionFactory;

	protected Session session;

	public HibernateDAOImpl() {

	}

	public Session getSession() {
		if (sessionFactory != null)
			session = sessionFactory.getCurrentSession();

		if (session == null)
			throw new RuntimeException("Hibernate Exception: Could not get the session.");

		return session;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}