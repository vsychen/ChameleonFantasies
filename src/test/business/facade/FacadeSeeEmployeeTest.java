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
import main.data.database.EmployeeDatabase;
import main.data.entities.Address;
import main.data.entities.Employee;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the SEE EMPLOYEE method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeSeeEmployeeTest {
	private Facade facade;
	private String cashierPassword;

	@Before
	public void setUp() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException,
			LoginErrorException, UnauthorizedActionException {
		this.facade = Facade.getInstance();
		String password = this.facade.firstAccess("Marcos da Silva", "222.222.222-22", "funcionario@ufncoinairo.com",
				"Avenida", "Cidade", "Estado", "Pais", 3000);

		this.facade.login("222.222.222-22", password);
		this.facade.addEmployee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "cashier", 1500);
		this.cashierPassword = this.facade.seeEmployee("444.555.666-00").getPassword();
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		new EmployeeDatabase().removeAll();

		this.facade.close();
	}

	// Test "simple SEE EMPLOYEE"
	@Test
	public void testSeeEmployee01()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com",
				new Address("Avenida", "Cidade", "Estado", "Pais"), "cashier", 1500);

		Assert.assertEquals(e.toString(), this.facade.seeEmployee("444.555.666-00").toString());
	}

	// Test "simple SEE EMPLOYEE in database with multiple employees"
	@Test
	public void testSeeEmployee02()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.addEmployee("Luana Medeiros", "111.222.333-00", "func1@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "stock", 1500);
		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com",
				new Address("Avenida", "Cidade", "Estado", "Pais"), "cashier", 1500);

		Assert.assertEquals(e.toString(), this.facade.seeEmployee("444.555.666-00").toString());
	}

	// Test "SEE EMPLOYEE when not logged in"
	@Test
	public void testSeeEmployee03()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();

		try {
			this.facade.seeEmployee("444.555.666-00");
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade SEE EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}

	// Test "SEE EMPLOYEE when not an admin"
	@Test
	public void testSeeEmployee04()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();
		this.facade.login("444.555.666-00", cashierPassword);

		try {
			this.facade.seeEmployee("444.555.666-00");
		} catch (UnauthorizedActionException e) {
			System.out.println("UnauthorizedActionException - Facade SEE EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("A conta logada não possui permissão para realizar esta ação.", e.getMessage());
		}
	}

	// Test "SEE EMPLOYEE with invalid cpf"
	@Test
	public void testAddEmployee05() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.seeEmployee("4");
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade SEE EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo CPF não foi inserido de forma correta.", e.getMessage());
		}
	}
}