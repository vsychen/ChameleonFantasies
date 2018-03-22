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
import main.data.entities.Customer;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the ADD CUSTOMER method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeAddCustomerTest {
	private Facade facade;
	private String adminPassword;

	@Before
	public void setUp() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException,
			LoginErrorException, UnauthorizedActionException {
		this.facade = Facade.getInstance();
		String password = this.adminPassword = this.facade.firstAccess("Marcos da Silva", "222.222.222-22",
				"funcionario@ufncoinairo.com", "Avenida", "Cidade", "Estado", "Pais", 3000);

		this.facade.login("222.222.222-22", password);
		this.facade.addEmployee("Simone Simaria", "111.111.111-11", "ss@func.com", "Avenida", "Cidade", "Estado",
				"Pais", "cashier", 1500);
		password = this.facade.seeEmployee("111.111.111-11").getPassword();

		this.facade.logout();
		this.facade.login("111.111.111-11", password);
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		new CustomerDatabase().removeAll();
		new EmployeeDatabase().removeAll();

		this.facade.close();
	}

	// Test "simple ADD CUSTOMER"
	@Test
	public void testAddCustomer01()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.addCustomer("Marina Camargo", "999.999.999-99");
		Customer c = new Customer("Marina Camargo", "999.999.999-99");

		Assert.assertEquals(c.toString(), this.facade.seeCustomer("999.999.999-99").toString());
	}

	// Test "consecutive ADD CUSTOMER"
	@Test
	public void testAddCustomer02()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.addCustomer("Marina Camargo", "999.999.999-99");
		this.facade.addCustomer("Hélio de Lima", "888.888.888-88");
		Customer c = new Customer("Hélio de Lima", "888.888.888-88");

		Assert.assertEquals(c.toString(), this.facade.seeCustomer("888.888.888-88").toString());
	}

	// Test "ADD CUSTOMER when not logged in"
	@Test
	public void testAddCustomer03()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();

		try {
			this.facade.addCustomer("Marina Camargo", "999.999.999-99");
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade ADD CUSTOMER: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}

	// Test "ADD CUSTOMER when not a cashier"
	@Test
	public void testAddCustomer04()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.adminPassword);

		try {
			this.facade.addCustomer("Marina Camargo", "999.999.999-99");
		} catch (UnauthorizedActionException e) {
			System.out.println("UnauthorizedActionException - Facade ADD CUSTOMER: " + e.getMessage());
			Assert.assertEquals("A conta logada não possui permissão para realizar esta ação.", e.getMessage());
		}
	}

	// Test "ADD CUSTOMER with invalid name"
	@Test
	public void testAddCustomer05() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addCustomer("Al", "999.999.999-99");
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD CUSTOMER: " + e.getMessage());
			Assert.assertEquals("O campo NOME não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD CUSTOMER with invalid cpf"
	@Test
	public void testAddCustomer06() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addCustomer("Marina Camargo", "9");
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD CUSTOMER: " + e.getMessage());
			Assert.assertEquals("O campo CPF não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD CUSTOMER with multiple invalid fields"
	@Test
	public void testAddCustomer07() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addCustomer("M", "9");
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD CUSTOMER: " + e.getMessage());
			Assert.assertEquals("Os campos NOME, CPF não foram inseridos de forma correta.", e.getMessage());
		}
	}
}