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
 * Tests for the SEE CUSTOMER method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeSeeCustomerTest {
	private Facade facade;
	private String adminPassword;
	private String cashierPassword;
	private String stockPassword;

	@Before
	public void setUp() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException,
			LoginErrorException, UnauthorizedActionException {
		this.facade = Facade.getInstance();
		this.adminPassword = this.facade.firstAccess("Marcos da Silva", "222.222.222-22", "funcionario@ufncoinairo.com",
				"Avenida", "Cidade", "Estado", "Pais", 3000);

		this.facade.login("222.222.222-22", this.adminPassword);
		this.facade.addEmployee("Simone Simaria", "111.111.111-11", "ss@func.com", "Avenida", "Cidade", "Estado",
				"Pais", "cashier", 1500);
		this.cashierPassword = this.facade.seeEmployee("111.111.111-11").getPassword();

		this.facade.addEmployee("Marcos da Silva", "333.333.333-33", "func2@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "stock", 1500);
		this.stockPassword = this.facade.seeEmployee("333.333.333-33").getPassword();

		this.facade.logout();
		this.facade.login("111.111.111-11", this.cashierPassword);
		this.facade.addCustomer("Marina Camargo", "999.999.999-99");
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		new CustomerDatabase().removeAll();
		new EmployeeDatabase().removeAll();

		this.facade.close();
	}

	// Test "simple SEE CUSTOMER"
	@Test
	public void testSeeCustomer01()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		Customer c = new Customer("Marina Camargo", "999.999.999-99");

		Assert.assertEquals(c.toString(), this.facade.seeCustomer("999.999.999-99").toString());
	}

	// Test "simple SEE CUSTOMER in database with multiple customers"
	@Test
	public void testSeeCustomer02()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.addCustomer("Hélio de Lima", "888.888.888-88");
		Customer c = new Customer("Marina Camargo", "999.999.999-99");

		Assert.assertEquals(c.toString(), this.facade.seeCustomer("999.999.999-99").toString());
	}

	// Test "SEE CUSTOMER when not logged in"
	@Test
	public void testSeeCustomer03()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();

		try {
			this.facade.seeCustomer("999.999.999-99");
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade SEE CUSTOMER: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}

	// Test "SEE CUSTOMER when not an admin or a cashier"
	@Test
	public void testSeeCustomer04()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();
		this.facade.login("333.333.333-33", this.stockPassword);

		try {
			this.facade.seeCustomer("999.999.999-99");
		} catch (UnauthorizedActionException e) {
			System.out.println("UnauthorizedActionException - Facade SEE CUSTOMER: " + e.getMessage());
			Assert.assertEquals("A conta logada não possui permissão para realizar esta ação.", e.getMessage());
		}
	}

	// Test "SEE CUSTOMER with invalid cpf"
	@Test
	public void testSeeCustomer05() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.seeCustomer("9");
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade SEE CUSTOMER: " + e.getMessage());
			Assert.assertEquals("O campo CPF não foi inserido de forma correta.", e.getMessage());
		}
	}
}