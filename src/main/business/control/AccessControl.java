package main.business.control;

import main.business.entities.Session;
import main.business.exceptions.LoginErrorException;
import main.data.database.IDatabase;
import main.data.entities.Customer;
import main.data.entities.Employee;
import main.data.entities.Fantasy;
import main.data.exceptions.DatabaseErrorException;

public class AccessControl {
	private IDatabase<Customer> cd;
	private IDatabase<Employee> ed;
	private IDatabase<Fantasy> fd;

	public AccessControl(IDatabase<Customer> cd, IDatabase<Employee> ed, IDatabase<Fantasy> fd) {
		this.cd = cd;
		this.ed = ed;
		this.fd = fd;
	}

	public Session login(String cpf, String password) throws LoginErrorException {
		Session s = null;
		Employee emp;

		try {
			if ((emp = this.ed.search(cpf)).getPassword().equals(password))
				s = new Session(cpf, password, emp.getJob());
			else
				throw new LoginErrorException("O cpf e/ou a senha foi digitada incorretamente.");
		} catch (DatabaseErrorException e) {
			throw new LoginErrorException("O cpf e/ou a senha foi digitada incorretamente.");
		}

		return s;
	}

	public Session logout() {
		return null;
	}

	public String remove(String code, int selector) throws DatabaseErrorException {
		if (selector == 0)
			this.cd.remove(code);
		else if (selector == 1)
			this.ed.remove(code);
		else if (selector == 2)
			this.fd.remove(code);

		return code;
	}

	public String getReport(int selector) throws DatabaseErrorException {
		String report = "";

		if (selector == 0)
			report = cd.list();
		else if (selector == 1)
			report = ed.list();
		else if (selector == 2)
			report = fd.list();

		return report;
	}
}