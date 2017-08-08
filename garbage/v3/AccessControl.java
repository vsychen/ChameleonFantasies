package br.com.chameleonfantasies.model.control;

import java.sql.SQLException;

import br.com.chameleonfantasies.model.database.EmployeeSQL;
import br.com.chameleonfantasies.model.entities.Employee;
import br.com.chameleonfantasies.model.entities.Session;
import br.com.chameleonfantasies.model.exceptions.LoginErrorException;

public class AccessControl {
	private EmployeeSQL esql;

	public AccessControl(String user, String pass, String path) {
		this.esql = new EmployeeSQL();
		this.esql.setUser(user).setPass(pass).setPathToConnect(path);
	}

	public Session login(String cpf, String password) throws LoginErrorException {
		Session s = null;
		Employee e;

		try {
			if ((e = this.esql.searchByCpf(cpf)).getPassword().equals(password))
				s = new Session(cpf, password, e.getJob());
			else
				throw new LoginErrorException("O cpf e/ou a senha foi digitada incorretamente.");
		} catch (SQLException sqle) {
			throw new LoginErrorException("O cpf e/ou a senha foi digitada incorretamente.");
		}

		return s;
	}

	public Session logout() {
		return null;
	}
}