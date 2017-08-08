package main.business.facade;

import main.business.control.AccessControl;
import main.business.control.CustomerControl;
import main.business.control.EmployeeControl;
import main.business.control.FantasyControl;
import main.business.entities.Session;
import main.business.exceptions.InsufficientStockException;
import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.NotFirstAccountException;
import main.business.exceptions.UnauthorizedActionException;
import main.data.database.CustomerDatabase;
import main.data.database.EmployeeDatabase;
import main.data.database.FantasyDatabase;
import main.data.entities.Customer;
import main.data.entities.Employee;
import main.data.entities.Fantasy;
import main.data.exceptions.DatabaseErrorException;

public class Facade {
	private final double MIN_SALARY = 700;

	private static Facade facade;
	private static Session session;

	private AccessControl ac;
	private CustomerControl cc;
	private EmployeeControl ec;
	private FantasyControl fc;

	private CustomerDatabase cd;
	private EmployeeDatabase ed;
	private FantasyDatabase fd;

	private Facade() {
		session = null;

		cd = new CustomerDatabase();
		ed = new EmployeeDatabase();
		fd = new FantasyDatabase();

		ac = new AccessControl(cd, ed, fd);
		cc = new CustomerControl(cd);
		ec = new EmployeeControl(ed);
		fc = new FantasyControl(fd);
	}

	public static synchronized Facade getInstance() {
		return (facade == null) ? new Facade() : facade;
	}

	public static synchronized boolean isLogged() {
		return (session == null) ? false : true;
	}

	public synchronized String firstAccess(String name, String cpf, String email, String street, String city,
			String state, String country, double salary)
					throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException {
		StringBuffer fields = new StringBuffer();

		if (name.length() < 5 || name.length() > 30)
			fields.append("NOME ");
		if (cpf.length() != 14)
			fields.append("CPF ");
		if (!email.contains("@"))
			fields.append("EMAIL ");
		if (street.length() < 5 || street.length() > 50)
			fields.append("RUA ");
		if (city.length() < 3 || city.length() > 20)
			fields.append("CIDADE ");
		if (state.length() < 2 || state.length() > 20)
			fields.append("ESTADO ");
		if (country.length() < 4 || country.length() > 20)
			fields.append("PAÍS ");
		if (salary <= MIN_SALARY)
			fields.append("SALÁRIO ");

		if (fields.length() > 0)
			throw new InvalidFieldException(createInvalidFieldMsg(fields));

		return ec.firstAccess(name, cpf, email, street, city, state, country, salary);
	}

	public void login(String cpf, String password) throws LoginErrorException, InvalidFieldException {
		if (isLogged())
			throw new LoginErrorException("Já há uma conta logada no sistema.");

		StringBuffer fields = new StringBuffer();

		if (cpf.length() != 14)
			fields.append("CPF ");
		if (password.length() != 4)
			fields.append("SENHA ");

		if (fields.length() > 0)
			throw new InvalidFieldException(createInvalidFieldMsg(fields));

		session = ac.login(cpf, password);
	}

	public void logout() throws LoginErrorException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");

		session = ac.logout();
	}

	public void addCustomer(String name, String cpf)
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");
		else if (!session.getJob().equals("cashier"))
			throw new UnauthorizedActionException();

		StringBuffer fields = new StringBuffer();

		if (name.length() < 5 || name.length() > 30)
			fields.append("NOME ");
		if (cpf.length() != 14)
			fields.append("CPF ");

		if (fields.length() > 0)
			throw new InvalidFieldException(createInvalidFieldMsg(fields));

		cc.addCustomer(name, cpf);
	}

	public void addEmployee(String name, String cpf, String email, String street, String city, String state,
			String country, String job, double salary) throws LoginErrorException, UnauthorizedActionException,
					DatabaseErrorException, InvalidFieldException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");
		else if (!session.getJob().equals("admin"))
			throw new UnauthorizedActionException();

		StringBuffer fields = new StringBuffer();

		if (name.length() < 5 || name.length() > 30)
			fields.append("NOME ");
		if (cpf.length() != 14)
			fields.append("CPF ");
		if (!email.contains("@"))
			fields.append("EMAIL ");
		if (street.length() < 5 || street.length() > 50)
			fields.append("RUA ");
		if (city.length() < 3 || city.length() > 20)
			fields.append("CIDADE ");
		if (state.length() < 2 || state.length() > 20)
			fields.append("ESTADO ");
		if (country.length() < 4 || country.length() > 20)
			fields.append("PAÍS ");
		if (!job.equals("admin") && !job.equals("cashier") && !job.equals("stock"))
			fields.append("CARGO ");
		if (salary <= MIN_SALARY)
			fields.append("SALÁRIO ");

		if (fields.length() > 0)
			throw new InvalidFieldException(createInvalidFieldMsg(fields));

		ec.addEmployee(name, cpf, email, street, city, state, country, job, salary);
	}

	public void addFantasy(String name, String id, Object[] hat, Object[] top, Object[] bottom, Object[] arms,
			Object[] shoes, int quantity, double buyPrice, double sellPrice) throws LoginErrorException,
					UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");
		else if (!session.getJob().equals("admin") && !session.getJob().equals("stock"))
			throw new UnauthorizedActionException();

		StringBuffer fields = new StringBuffer();

		if (name.length() < 2 || name.length() > 30)
			fields.append("NOME ");
		if (quantity < 0)
			fields.append("QUANTIDADE ");
		if (buyPrice <= 0)
			fields.append("PREÇODECOMPRA ");
		if (sellPrice <= buyPrice)
			fields.append("PREÇODEVENDA ");

		if (fields.length() > 0)
			throw new InvalidFieldException(createInvalidFieldMsg(fields));

		fc.addFantasy(name, id, hat, top, bottom, arms, shoes, quantity, buyPrice, sellPrice);
	}

	public void editEmployee(String cpf, String email, String street, String city, String state, String country,
			String job, double salary) throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException,
					InvalidFieldException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");
		else if (!session.getJob().equals("admin"))
			throw new UnauthorizedActionException();

		StringBuffer fields = new StringBuffer();

		if (cpf.length() != 14)
			fields.append("CPF ");
		if (!email.contains("@"))
			fields.append("EMAIL ");
		if (street.length() < 5 || street.length() > 50)
			fields.append("RUA ");
		if (city.length() < 3 || city.length() > 20)
			fields.append("CIDADE ");
		if (state.length() < 2 || state.length() > 20)
			fields.append("ESTADO ");
		if (country.length() < 4 || country.length() > 20)
			fields.append("PAÍS ");
		if (!job.equals("admin") && !job.equals("cashier") && !job.equals("stock"))
			fields.append("CARGO ");
		if (salary <= MIN_SALARY)
			fields.append("SALÁRIO ");

		if (fields.length() > 0)
			throw new InvalidFieldException(createInvalidFieldMsg(fields));

		ec.editEmployee(cpf, email, street, city, state, country, job, salary);
	}

	public void buyFantasy(String id, int quantity)
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");
		else if (!session.getJob().equals("stock"))
			throw new UnauthorizedActionException();

		if (quantity <= 0)
			throw new InvalidFieldException("O campo QUANTIDADE não foi inserido de forma correta.");

		fc.buyFantasy(id, quantity);
	}

	public void sellFantasy(String cpf, String id, int quantity) throws LoginErrorException,
			UnauthorizedActionException, InvalidFieldException, DatabaseErrorException, InsufficientStockException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");
		else if (!session.getJob().equals("cashier"))
			throw new UnauthorizedActionException();

		StringBuffer fields = new StringBuffer();

		if (cpf.length() != 14)
			fields.append("CPF ");
		if (quantity <= 0)
			fields.append("QUANTIDADE ");

		if (fields.length() > 0)
			throw new InvalidFieldException(createInvalidFieldMsg(fields));

		fc.sellFantasy(this.cd, cpf, id, quantity);
	}

	public void changePrices(String id, double buyPrice, double sellPrice)
			throws LoginErrorException, UnauthorizedActionException, InvalidFieldException, DatabaseErrorException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");
		else if (!session.getJob().equals("admin"))
			throw new UnauthorizedActionException();
		StringBuffer fields = new StringBuffer();

		if (buyPrice < 0)
			fields.append("PREÇODECOMPRA ");
		if (sellPrice < 0)
			fields.append("PREÇODEVENDA ");
		if (sellPrice > 0 && buyPrice >= sellPrice) {
			fields.append("PREÇODECOMPRA ");
			fields.append("PREÇODEVENDA ");
		}

		if (fields.length() > 0)
			throw new InvalidFieldException(createInvalidFieldMsg(fields));

		fc.changePrice(id, buyPrice, sellPrice);
	}

	public Customer seeCustomer(String cpf)
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");
		else if (!session.getJob().equals("admin") && !session.getJob().equals("cashier"))
			throw new UnauthorizedActionException();

		if (cpf.length() != 14)
			throw new InvalidFieldException("O campo CPF não foi inserido de forma correta.");

		return cc.searchCustomer(cpf);
	}

	public Employee seeEmployee(String cpf)
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");
		else if (!session.getJob().equals("admin"))
			throw new UnauthorizedActionException();

		if (cpf.length() != 14)
			throw new InvalidFieldException("O campo CPF não foi inserido de forma correta.");

		return ec.searchEmployee(cpf);
	}

	public Fantasy seeFantasy(String id)
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");
		else if (!session.getJob().equals("admin") && !session.getJob().equals("stock")
				&& !session.getJob().equals("cashier"))
			throw new UnauthorizedActionException();

		return fc.searchFantasy(id);
	}

	public String removeWrongAdditions(String code, int selector)
			throws LoginErrorException, UnauthorizedActionException, InvalidFieldException, DatabaseErrorException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");
		else if (!session.getJob().equals("admin"))
			throw new UnauthorizedActionException();

		if ((selector == 0 || selector == 1) && code.length() != 14)
			throw new InvalidFieldException("O campo CPF não foi inserido de forma correta.");

		return ac.remove(code, selector);
	}

	public String getReport(int selector)
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		if (!isLogged())
			throw new LoginErrorException("Não há conta logada no sistema.");
		else if (!session.getJob().equals("admin"))
			throw new UnauthorizedActionException();

		return ac.getReport(selector);
	}

	private String createInvalidFieldMsg(StringBuffer fields) {
		fields = fields.deleteCharAt(fields.lastIndexOf(" "));
		String errorMsg = "";

		if (fields.indexOf(" ") == -1)
			errorMsg = "O campo " + fields.toString() + " não foi inserido de forma correta.";
		else
			errorMsg = "Os campos " + fields.toString().replace(" ", ", ") + " não foram inseridos de forma correta.";

		return errorMsg;
	}

	public void close() throws LoginErrorException {
		if (isLogged())
			logout();

		facade = null;
	}
}