package main.business.control;

import main.business.exceptions.NotFirstAccountException;
import main.data.database.IDatabase;
import main.data.entities.Address;
import main.data.entities.Employee;
import main.data.exceptions.DatabaseErrorException;

public class EmployeeControl {
	private IDatabase<Employee> ed;

	public EmployeeControl(IDatabase<Employee> ed) {
		this.ed = ed;
	}

	public String firstAccess(String name, String cpf, String email, String street, String city, String state,
			String country, double salary) throws DatabaseErrorException, NotFirstAccountException {
		if (this.ed.list().isEmpty()) {
			Employee e = new Employee(name, cpf, email, new Address(street, city, state, country), "admin", salary);
			this.ed.add(e);
			sendEmail(e.getEmail(), e.getPassword());
			return e.getPassword();
		} else {
			throw new NotFirstAccountException();
		}
	}

	public void addEmployee(String name, String cpf, String email, String street, String city, String state,
			String country, String job, double salary) throws DatabaseErrorException {
		Employee e = new Employee(name, cpf, email, new Address(street, city, state, country), job, salary);
		this.ed.add(e);
		sendEmail(e.getEmail(), e.getPassword());
	}

	public void editEmployee(String cpf, String email, String street, String city, String state, String country,
			String job, double salary) throws DatabaseErrorException {
		Employee e = this.ed.search(cpf);

		if (email != null && !email.equals(""))
			e.setEmail(email);

		Address a = e.getAddress();

		if (street != null && !street.equals(""))
			a.setStreet(street);

		if (city != null && !city.equals(""))
			a.setCity(city);

		if (state != null && !state.equals(""))
			a.setState(state);

		if (country != null && !country.equals(""))
			a.setCountry(country);

		e.setAddress(a);

		if (job != null && !job.equals(""))
			e.setJob(job);

		if (salary > e.getSalary() && salary < (e.getSalary() * 2))
			e.setSalary(salary);

		this.ed.edit(cpf, e);
	}

	public Employee searchEmployee(String cpf) throws DatabaseErrorException {
		return this.ed.search(cpf);
	}

	private void sendEmail(String email, String password) {
		// TODO SEND EMAIL
	}
}