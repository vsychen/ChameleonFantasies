package br.com.chameleonfantasies.model.facade;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.List;

import br.com.chameleonfantasies.model.control.AccessControl;
import br.com.chameleonfantasies.model.control.CustomerControl;
import br.com.chameleonfantasies.model.control.EmployeeControl;
import br.com.chameleonfantasies.model.control.FantasyControl;
import br.com.chameleonfantasies.model.entities.Customer;
import br.com.chameleonfantasies.model.entities.Employee;
import br.com.chameleonfantasies.model.entities.Fantasy;
import br.com.chameleonfantasies.model.entities.Session;
import br.com.chameleonfantasies.model.exceptions.InsufficientStockException;
import br.com.chameleonfantasies.model.exceptions.InvalidFieldException;
import br.com.chameleonfantasies.model.exceptions.LoginErrorException;
import br.com.chameleonfantasies.model.exceptions.NotFirstAccountException;
import br.com.chameleonfantasies.model.exceptions.NotLoggedInException;
import br.com.chameleonfantasies.model.exceptions.UnauthorizedActionException;

public class Facade {
	private static final double MIN_SALARY = 700;
	private static final String PATH_TO_LOG = "../v3/log.txt";

	private Facade facade;
	private AccessControl ac;
	private CustomerControl cc;
	private EmployeeControl ec;
	private FantasyControl fc;

	private Session session;

	private final String DB_USER = "cf";
	private final String DB_PASS = "1234";
	private final String DB_PATH = "chameleonfantasies";

	private Facade() {
		ac = new AccessControl(DB_USER, DB_PASS, DB_PATH);
		cc = new CustomerControl(DB_USER, DB_PASS, DB_PATH);
		ec = new EmployeeControl(DB_USER, DB_PASS, DB_PATH);
		fc = new FantasyControl(DB_USER, DB_PASS, DB_PATH);
	}

	public Facade getInstance() {
		return (this.facade == null) ? new Facade() : this.facade;
	}

	public Session getSession() {
		return session;
	}

	public void login(String user, String pass) throws LoginErrorException {
		this.session = this.ac.login(user, pass);
	}

	public void logout() throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PATH_TO_LOG), "UTF-8"));
		out.write(this.session.toString());
		out.close();
		this.session = this.ac.logout();
	}

	public void addCustomer(String name, String cpf)
			throws NotLoggedInException, UnauthorizedActionException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.getJob().equals("admin") && !this.session.getJob().equals("cashier"))
			throw new UnauthorizedActionException();
		else if (name.length() < 5 || name.length() > 30 || cpf.length() != 14)
			throw new InvalidFieldException();

		this.cc.add(name, cpf);
	}

	public Customer searchCustomer(String cpf)
			throws NotLoggedInException, UnauthorizedActionException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.getJob().equals("admin") && !this.session.getJob().equals("cashier"))
			throw new UnauthorizedActionException();
		else if (cpf.length() != 14)
			throw new InvalidFieldException();

		return this.cc.search(cpf);
	}

	public boolean removeCustomer(String cpf)
			throws NotLoggedInException, UnauthorizedActionException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.getJob().equals("admin"))
			throw new UnauthorizedActionException();
		else if (cpf.length() != 14)
			throw new InvalidFieldException();

		return this.cc.remove(cpf);
	}

	public List<Customer> listCustomers() throws NotLoggedInException, UnauthorizedActionException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.getJob().equals("admin"))
			throw new UnauthorizedActionException();

		return this.cc.list();
	}

	public void addFantasy(String name, Object[] hat, Object[] top, Object[] bottom, Object[] arms, Object[] shoes,
			int quantity, double buyPrice, double sellPrice)
					throws NotLoggedInException, UnauthorizedActionException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.getJob().equals("admin") && !this.session.getJob().equals("stock"))
			throw new UnauthorizedActionException();
		else if (name.length() < 5 || name.length() > 30 || quantity < 0 || buyPrice <= 0 || buyPrice >= sellPrice)
			throw new InvalidFieldException();

		this.fc.add(name, hat, top, bottom, arms, shoes, quantity, buyPrice, sellPrice);
	}

	public void buyFantasy(Long id, int quantity)
			throws NotLoggedInException, UnauthorizedActionException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.equals("stock"))
			throw new UnauthorizedActionException();
		else if (id <= 0 || quantity < 0)
			throw new InvalidFieldException();

		this.fc.buyFantasy(id, quantity);
	}

	public void sellFantasy(Long id, String cpf, int quantity) throws NotLoggedInException, UnauthorizedActionException,
			InvalidFieldException, SQLException, InsufficientStockException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.equals("cashier"))
			throw new UnauthorizedActionException();
		else if (id <= 0 || cpf.length() != 14 || quantity < 0)
			throw new InvalidFieldException();

		this.fc.sellFantasy(id, cpf, quantity);
	}

	public void changePrices(Long id, double buyPrice, double sellPrice)
			throws NotLoggedInException, UnauthorizedActionException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.equals("admin"))
			throw new UnauthorizedActionException();
		else if (id <= 0 || buyPrice <= 0 || buyPrice >= sellPrice)
			throw new InvalidFieldException();

		this.fc.changePrices(id, buyPrice, sellPrice);
	}

	public Fantasy searchFantasyById(Long id) throws NotLoggedInException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (id <= 0)
			throw new InvalidFieldException();

		return this.fc.searchById(id);
	}

	public List<Fantasy> searchFantasyByName(String name)
			throws NotLoggedInException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (name.length() < 5 || name.length() > 30)
			throw new InvalidFieldException();

		return this.fc.searchByName(name);
	}

	public boolean removeFantasy(Long id)
			throws NotLoggedInException, UnauthorizedActionException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.equals("admin"))
			throw new UnauthorizedActionException();
		else if (id <= 0)
			throw new InvalidFieldException();

		return this.fc.remove(id);
	}

	public List<Fantasy> listFantasies() throws NotLoggedInException, UnauthorizedActionException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.getJob().equals("admin"))
			throw new UnauthorizedActionException();

		return this.fc.list();
	}

	public void firstAccess(String name, String cpf, String email, String street, String city, String state,
			String country, double salary) throws NotFirstAccountException, InvalidFieldException, SQLException {
		if (this.ec.list().size() != 0)
			throw new NotFirstAccountException();
		else if (name.length() < 5 || name.length() > 30 || cpf.length() != 14 || !email.contains("@")
				|| street.length() < 5 || city.length() < 5 || state.length() < 5 || country.length() < 4
				|| salary <= MIN_SALARY)
			throw new InvalidFieldException();

		this.ec.add(name, cpf, email, street, city, state, country, "admin", salary);
	}

	public void addEmployee(String name, String cpf, String email, String street, String city, String state,
			String country, String job, double salary)
					throws NotLoggedInException, UnauthorizedActionException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.equals("admin"))
			throw new UnauthorizedActionException();
		else if (name.length() < 5 || name.length() > 30 || cpf.length() != 14 || !email.contains("@")
				|| street.length() < 5 || city.length() < 5 || state.length() < 5 || country.length() < 4
				|| !(job.equals("admin") || job.equals("stock") || job.equals("cashier")) || salary <= MIN_SALARY)
			throw new InvalidFieldException();

		this.ec.add(name, cpf, email, street, city, state, country, job, salary);
	}

	public void editEmployee(String cpf, String email, String street, String city, String state, String country,
			String job, double salary)
					throws NotLoggedInException, UnauthorizedActionException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.getJob().equals("admin"))
			throw new UnauthorizedActionException();
		else if (cpf.length() != 14 || !email.contains("@") || street.length() < 5 || city.length() < 5
				|| state.length() < 5 || country.length() < 4
				|| !(job.equals("admin") || job.equals("stock") || job.equals("cashier")) || salary <= MIN_SALARY)
			throw new InvalidFieldException();

		this.ec.edit(cpf, email, street, city, state, country, job, salary);
	}

	public Employee searchEmployee(String cpf)
			throws NotLoggedInException, UnauthorizedActionException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.getJob().equals("admin"))
			throw new UnauthorizedActionException();
		else if (cpf.length() != 14)
			throw new InvalidFieldException();

		return this.ec.search(cpf);
	}

	public boolean removeEmployee(String cpf)
			throws NotLoggedInException, UnauthorizedActionException, InvalidFieldException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.getJob().equals("admin"))
			throw new UnauthorizedActionException();
		else if (cpf.length() != 14)
			throw new InvalidFieldException();

		return this.ec.remove(cpf);
	}

	public List<Employee> listEmployees() throws NotLoggedInException, UnauthorizedActionException, SQLException {
		if (this.session == null)
			throw new NotLoggedInException();
		else if (!this.session.getJob().equals("admin"))
			throw new UnauthorizedActionException();

		return this.ec.list();
	}
}