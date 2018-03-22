package br.com.chameleonfantasies.model.control;

import java.sql.SQLException;
import java.util.List;

import br.com.chameleonfantasies.model.database.CustomerSQL;
import br.com.chameleonfantasies.model.entities.Customer;

public class CustomerControl {
	private CustomerSQL csql;

	public CustomerControl(String user, String pass, String path) {
		this.csql = new CustomerSQL();
		this.csql.setUser(user).setPass(pass).setPathToConnect(path);
	}

	public void add(String name, String cpf) throws SQLException {
		Customer c = new Customer(name, cpf, 0);
		this.csql.insert(c);
	}

	public void edit(String cpf, double spending) throws SQLException {
		Customer c = this.csql.searchByCpf(cpf);
		c.setSpending(spending);
		this.csql.update(c);
	}

	public Customer search(String cpf) throws SQLException {
		return this.csql.searchByCpf(cpf);
	}

	public boolean remove(String cpf) throws SQLException {
		Customer c = this.csql.searchByCpf(cpf);
		return this.csql.remove(c.getId());
	}

	public List<Customer> list() throws SQLException {
		return this.csql.list();
	}
}