package br.com.cf.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface HibernateDAO<T> {

	public Session getSession();

	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory);

}