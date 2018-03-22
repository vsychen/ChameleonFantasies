package br.com.chameleonfantasies.model.control;

import java.sql.SQLException;
import java.util.List;

import br.com.chameleonfantasies.model.database.EmployeeSQL;
import br.com.chameleonfantasies.model.entities.Address;
import br.com.chameleonfantasies.model.entities.Employee;

public class EmployeeControl {
	private EmployeeSQL esql;

	public EmployeeControl(String user, String pass, String path) {
		this.esql = new EmployeeSQL();
		this.esql.setUser(user).setPass(pass).setPathToConnect(path);
	}

	public void add(String name, String cpf, String email, String street, String city, String state, String country,
			String job, double salary) throws SQLException {
		Address address = new Address(street, city, state, country);
		Employee e = new Employee(name, cpf, email, address, job, salary);
		this.esql.insert(e);
	}

	public void edit(String cpf, String email, String street, String city, String state, String country, String job,
			double salary) throws SQLException {
		Employee e = this.esql.searchByCpf(cpf);
		e.setEmail(email);
		e.setAddress(new Address(street, city, state, country));
		e.setJob(job);
		e.setSalary(salary);
		this.esql.update(e);
	}

	public Employee search(String cpf) throws SQLException {
		return this.esql.searchByCpf(cpf);
	}

	public boolean remove(String cpf) throws SQLException {
		Employee e = this.esql.searchByCpf(cpf);
		return this.esql.remove(e.getId());
	}

	public List<Employee> list() throws SQLException {
		return this.esql.list();
	}
}