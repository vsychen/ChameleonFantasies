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
 * Tests for the ADD EMPLOYEE method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeAddEmployeeTest {
	private Facade facade;
	private String password;

	@Before
	public void setUp() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException,
			LoginErrorException, UnauthorizedActionException {
		this.facade = Facade.getInstance();
		this.password = this.facade.firstAccess("Marcos da Silva", "222.222.222-22", "funcionario@ufncoinairo.com",
				"Avenida", "Cidade", "Estado", "Pais", 3000);
		this.facade.login("222.222.222-22", password);
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		new EmployeeDatabase().removeAll();

		this.facade.close();
	}

	// Test "simple ADD EMPLOYEE"
	@Test
	public void testAddEmployee01()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.addEmployee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "cashier", 1500);
		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com",
				new Address("Avenida", "Cidade", "Estado", "Pais"), "cashier", 1500);

		Assert.assertEquals(e.toString(), this.facade.seeEmployee("444.555.666-00").toString());
	}

	// Test "consecutive ADD EMPLOYEE"
	@Test
	public void testAddEmployee02()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.addEmployee("Luana Medeiros", "111.222.333-00", "func1@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "cashier", 1500);
		this.facade.addEmployee("Marcos da Silva", "444.555.666-00", "func2@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "stock", 1500);
		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "func2@ufncoinairo.com",
				new Address("Avenida", "Cidade", "Estado", "Pais"), "stock", 1500);

		Assert.assertEquals(e.toString(), this.facade.seeEmployee("444.555.666-00").toString());
	}

	// Test "ADD EMPLOYEE when not logged in"
	@Test
	public void testAddEmployee03()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();

		try {
			this.facade.addEmployee("Marcos da Silva", "444.555.666-00", "func2@ufncoinairo.com", "Avenida", "Cidade",
					"Estado", "Pais", "stock", 1500);
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE when not an admin"
	@Test
	public void testAddEmployee04()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.addEmployee("Marcos da Silva", "444.555.666-00", "func2@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "stock", 1500);
		String password = this.facade.seeEmployee("444.555.666-00").getPassword();
		this.facade.logout();
		this.facade.login("444.555.666-00", password);

		try {
			this.facade.addEmployee("Marcos da Silva", "111.222.333-00", "func2@ufncoinairo.com", "Avenida", "Cidade",
					"Estado", "Pais", "stock", 1500);
		} catch (UnauthorizedActionException e) {
			System.out.println("UnauthorizedActionException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("A conta logada não possui permissão para realizar esta ação.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE with invalid name"
	@Test
	public void testAddEmployee05() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addEmployee("Al", "111.222.333-00", "func2@ufncoinairo.com", "Avenida", "Cidade", "Estado",
					"Pais", "stock", 1500);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo NOME não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE with invalid cpf"
	@Test
	public void testAddEmployee06() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addEmployee("Marcos da Silva", "1", "func2@ufncoinairo.com", "Avenida", "Cidade", "Estado",
					"Pais", "stock", 1500);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo CPF não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE with invalid email"
	@Test
	public void testAddEmployee07() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addEmployee("Marcos da Silva", "111.222.333-00", "func2", "Avenida", "Cidade", "Estado", "Pais",
					"stock", 1500);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo EMAIL não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE with invalid street"
	@Test
	public void testAddEmployee08() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addEmployee("Marcos da Silva", "111.222.333-00", "func2@ufncoinairo.com", "Rua", "Cidade",
					"Estado", "Pais", "stock", 1500);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo RUA não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE with invalid city"
	@Test
	public void testAddEmployee09() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addEmployee("Marcos da Silva", "111.222.333-00", "func2@ufncoinairo.com", "Avenida", "La",
					"Estado", "Pais", "stock", 1500);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo CIDADE não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE with invalid state"
	@Test
	public void testAddEmployee10() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addEmployee("Marcos da Silva", "111.222.333-00", "func2@ufncoinairo.com", "Avenida", "Cidade",
					"E", "Pais", "stock", 1500);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo ESTADO não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE with invalid country"
	@Test
	public void testAddEmployee11() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addEmployee("Marcos da Silva", "111.222.333-00", "func2@ufncoinairo.com", "Avenida", "Cidade",
					"Estado", "P", "stock", 1500);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo PAÍS não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE with invalid job"
	@Test
	public void testAddEmployee12() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addEmployee("Marcos da Silva", "111.222.333-00", "func2@ufncoinairo.com", "Avenida", "Cidade",
					"Estado", "Pais", "coveiro", 1500);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo CARGO não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE with invalid salary"
	@Test
	public void testAddEmployee13() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addEmployee("Marcos da Silva", "111.222.333-00", "func2@ufncoinairo.com", "Avenida", "Cidade",
					"Estado", "Pais", "stock", 0);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo SALÁRIO não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE with negative salary"
	@Test
	public void testAddEmployee14() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addEmployee("Marcos da Silva", "111.222.333-00", "func2@ufncoinairo.com", "Avenida", "Cidade",
					"Estado", "Pais", "stock", -1);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo SALÁRIO não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE with low salary"
	@Test
	public void testAddEmployee15() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addEmployee("Marcos da Silva", "111.222.333-00", "func2@ufncoinairo.com", "Avenida", "Cidade",
					"Estado", "Pais", "stock", 300);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo SALÁRIO não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD EMPLOYEE with multiple invalid fields"
	@Test
	public void testAddEmployee16() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.addEmployee("M", "1", "f", "A", "C", "E", "P", "s", 300);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD EMPLOYEE: " + e.getMessage());
			Assert.assertEquals(
					"Os campos NOME, CPF, EMAIL, RUA, CIDADE, ESTADO, PAÍS, CARGO, SALÁRIO não foram inseridos de forma correta.",
					e.getMessage());
		}
	}
}