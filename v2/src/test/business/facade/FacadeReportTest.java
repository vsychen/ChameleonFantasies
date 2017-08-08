package test.business.facade;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.NotFirstAccountException;
import main.business.exceptions.UnauthorizedActionException;
import main.business.facade.Facade;
import main.data.database.CustomerDatabase;
import main.data.database.EmployeeDatabase;
import main.data.database.FantasyDatabase;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the REPORT method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeReportTest {
	private Facade facade;
	private String adminPassword;
	private String cashierPassword;

	@Before
	public void setUp() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException,
			LoginErrorException, UnauthorizedActionException {
		this.facade = Facade.getInstance();
		this.adminPassword = this.facade.firstAccess("Marcos da Silva", "111.111.111-11", "funcionario@ufncoinairo.com",
				"Avenida", "Cidade", "Estado", "Pais", 3000);

		this.facade.login("111.111.111-11", adminPassword);
		this.facade.addEmployee("Luana Medeiros", "222.222.222-22", "func1@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "cashier", 1500);
		this.cashierPassword = this.facade.seeEmployee("222.222.222-22").getPassword();
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		new CustomerDatabase().removeAll();
		new EmployeeDatabase().removeAll();
		new FantasyDatabase().removeAll();

		this.facade.close();
	}

	// Test "REPORT CUSTOMER"
	@Test
	public void testReport01()
			throws LoginErrorException, InvalidFieldException, UnauthorizedActionException, DatabaseErrorException {
		this.facade.logout();

		this.facade.login("222.222.222-22", this.cashierPassword);
		this.facade.addCustomer("Julio dos Santos", "999.999.999-99");

		this.facade.logout();
		this.facade.login("111.111.111-11", this.adminPassword);

		Assert.assertNotEquals("", this.facade.getReport(0));
	}

	// Test "REPORT CUSTOMERS"
	@Test
	public void testReport02()
			throws LoginErrorException, InvalidFieldException, UnauthorizedActionException, DatabaseErrorException {
		this.facade.logout();

		this.facade.login("222.222.222-22", this.cashierPassword);
		this.facade.addCustomer("Julio dos Santos", "999.999.999-99");
		this.facade.addCustomer("Maria de Fátima", "888.888.888-88");

		this.facade.logout();
		this.facade.login("111.111.111-11", this.adminPassword);

		Assert.assertNotEquals("", this.facade.getReport(0));
	}

	// Test "REPORT EMPLOYEES"
	@Test
	public void testReport03() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		Assert.assertNotEquals("", this.facade.getReport(1));
	}

	// Test "REPORT FANTASIES"
	@Test
	public void testReport04()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		Object[] aHat = { "Máscara", "Preta", false, false };
		Object[] aTop = { "Suéter", "Preto", true, false };
		Object[] aBottom = { "Calça", "Preta", false, false };
		Object[] aArms = { "Luva", "Preta", false, false };
		Object[] aShoes = { "Sandália", "Preta", false, false };
		Object[] aBottom2 = { "Calça", "Roxa", false, false };

		this.facade.addFantasy("Batman", "1", aHat, aTop, aBottom, aArms, aShoes, 10, 500, 1000);
		this.facade.addFantasy("Hulk", "2", null, null, aBottom2, null, null, 50, 200, 400);

		Assert.assertNotEquals("", this.facade.getReport(2));
	}

	// Test "REPORT when not logged in"
	@Test
	public void testReport05() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		this.facade.logout();

		try {
			this.facade.getReport(2);
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade REPORT: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}

	// Test "REPORT when not logged in as an admin"
	@Test
	public void testReport06() throws LoginErrorException, InvalidFieldException, DatabaseErrorException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.cashierPassword);

		try {
			this.facade.getReport(2);
		} catch (UnauthorizedActionException e) {
			System.out.println("UnauthorizedActionException - Facade REPORT: " + e.getMessage());
			Assert.assertEquals("A conta logada não possui permissão para realizar esta ação.", e.getMessage());
		}
	}
}