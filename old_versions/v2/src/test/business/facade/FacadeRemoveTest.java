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
 * Tests for the REMOVE method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeRemoveTest {
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

	// Test "REMOVE CUSTOMER"
	@Test(expected = DatabaseErrorException.class)
	public void testRemove01()
			throws LoginErrorException, InvalidFieldException, UnauthorizedActionException, DatabaseErrorException {
		this.facade.logout();

		this.facade.login("222.222.222-22", this.cashierPassword);
		this.facade.addCustomer("Julio dos Santos", "999.999.999-99");
		this.facade.logout();

		this.facade.login("111.111.111-11", this.adminPassword);
		this.facade.removeWrongAdditions("999.999.999-99", 0);
		this.facade.seeCustomer("999.999.999-99");
	}

	// Test "REMOVE EMPLOYEE"
	@Test(expected = DatabaseErrorException.class)
	public void testRemove02()
			throws LoginErrorException, UnauthorizedActionException, InvalidFieldException, DatabaseErrorException {
		this.facade.removeWrongAdditions("333.333.333-33", 1);
		this.facade.seeEmployee("333.333.333-33");
	}

	// Test "REMOVE FANTASY"
	@Test(expected = DatabaseErrorException.class)
	public void testRemove03()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		Object[] aHat = { "Máscara", "Preta", false, false };
		Object[] aTop = { "Suéter", "Preto", true, false };
		Object[] aBottom = { "Calça", "Preta", false, false };
		Object[] aArms = { "Luva", "Preta", false, false };
		Object[] aShoes = { "Sandália", "Preta", false, false };

		this.facade.addFantasy("Batman", "1", aHat, aTop, aBottom, aArms, aShoes, 10, 500, 1000);
		this.facade.removeWrongAdditions("1", 2);
		this.facade.seeFantasy("1");
	}

	// Test "REMOVE when not logged in"
	@Test
	public void testRemove04()
			throws LoginErrorException, UnauthorizedActionException, InvalidFieldException, DatabaseErrorException {
		this.facade.logout();

		try {
			this.facade.removeWrongAdditions("333.333.333-33", 1);
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade REMOVE: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}

	// Test "REMOVE when not logged in as an admin"
	@Test
	public void testRemove05() throws LoginErrorException, InvalidFieldException, DatabaseErrorException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.cashierPassword);

		try {
			this.facade.removeWrongAdditions("333.333.333-33", 1);
		} catch (UnauthorizedActionException e) {
			System.out.println("UnauthorizedActionException - Facade REMOVE: " + e.getMessage());
			Assert.assertEquals("A conta logada não possui permissão para realizar esta ação.", e.getMessage());
		}
	}

	// Test "REMOVE an employee with invalid cpf"
	@Test
	public void testRemove06()
			throws LoginErrorException, InvalidFieldException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.removeWrongAdditions("3", 1);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade REMOVE: " + e.getMessage());
			Assert.assertEquals("O campo CPF não foi inserido de forma correta.", e.getMessage());
		}
	}
}