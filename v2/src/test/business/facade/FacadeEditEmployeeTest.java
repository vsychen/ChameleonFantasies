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
 * Tests for the EDIT EMPLOYEE method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeEditEmployeeTest {
	private Facade facade;
	private String password;

	@Before
	public void setUp() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException,
			LoginErrorException, UnauthorizedActionException {
		this.facade = Facade.getInstance();
		this.password = this.facade.firstAccess("Marcos da Silva", "222.222.222-22", "funcionario@ufncoinairo.com",
				"Avenida", "Cidade", "Estado", "Pais", 3000);

		this.facade.login("222.222.222-22", password);
		this.facade.addEmployee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "cashier", 1500);
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		new EmployeeDatabase().removeAll();

		this.facade.close();
	}

	// Test "simple EDIT EMPLOYEE"
	@Test
	public void testEditEmployee01()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.editEmployee("444.555.666-00", "func@func.com", "Avenida das Laranjas", "Laranjeiras",
				"Suco de Laranja", "Doce de Laranja", "stock", 1800);
		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "func@func.com",
				new Address("Avenida das Laranjas", "Laranjeiras", "Suco de Laranja", "Doce de Laranja"), "stock",
				1800);

		Assert.assertEquals(e.toString(), this.facade.seeEmployee("444.555.666-00").toString());
	}

	// Test "simple EDIT EMPLOYEE in database with multiple employees"
	@Test
	public void testEditEmployee02()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.addEmployee("Luana Medeiros", "111.222.333-00", "func1@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "cashier", 1500);
		this.facade.editEmployee("444.555.666-00", "func@func.com", "Avenida das Laranjas", "Laranjeiras",
				"Suco de Laranja", "Doce de Laranja", "stock", 1800);
		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "func@func.com",
				new Address("Avenida das Laranjas", "Laranjeiras", "Suco de Laranja", "Doce de Laranja"), "stock",
				1800);

		Assert.assertEquals(e.toString(), this.facade.seeEmployee("444.555.666-00").toString());
	}

	// Test "EDIT EMPLOYEE when not logged in"
	@Test
	public void testEditEmployee03()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();

		try {
			this.facade.editEmployee("444.555.666-00", "func@func.com", "Avenida das Laranjas", "Laranjeiras",
					"Suco de Laranja", "Doce de Laranja", "stock", 1800);
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}

	// Test "EDIT EMPLOYEE when not an admin"
	@Test
	public void testEditEmployee04()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		String password = this.facade.seeEmployee("444.555.666-00").getPassword();
		this.facade.logout();
		this.facade.login("444.555.666-00", password);

		try {
			this.facade.editEmployee("444.555.666-00", "func@func.com", "Avenida das Laranjas", "Laranjeiras",
					"Suco de Laranja", "Doce de Laranja", "stock", 1800);
		} catch (UnauthorizedActionException e) {
			System.out.println("UnauthorizedActionException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("A conta logada não possui permissão para realizar esta ação.", e.getMessage());
		}
	}

	// Test "EDIT EMPLOYEE with invalid cpf"
	@Test
	public void testEditEmployee05() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.editEmployee("4", "func@func.com", "Avenida das Laranjas", "Laranjeiras", "Suco de Laranja",
					"Doce de Laranja", "stock", 1800);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo CPF não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "EDIT EMPLOYEE with invalid email"
	@Test
	public void testEditEmployee06() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.editEmployee("444.555.666-00", "func", "Avenida das Laranjas", "Laranjeiras", "Suco de Laranja",
					"Doce de Laranja", "stock", 1800);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo EMAIL não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "EDIT EMPLOYEE with invalid street"
	@Test
	public void testEditEmployee07() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.editEmployee("444.555.666-00", "func@func.com", "A", "Laranjeiras", "Suco de Laranja",
					"Doce de Laranja", "stock", 1800);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo RUA não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "EDIT EMPLOYEE with invalid city"
	@Test
	public void testEditEmployee08() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.editEmployee("444.555.666-00", "func@func.com", "Avenida das Laranjas", "L", "Suco de Laranja",
					"Doce de Laranja", "stock", 1800);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo CIDADE não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "EDIT EMPLOYEE with invalid state"
	@Test
	public void testEditEmployee09() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.editEmployee("444.555.666-00", "func@func.com", "Avenida das Laranjas", "Laranjeiras", "S",
					"Doce de Laranja", "stock", 1800);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo ESTADO não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "EDIT EMPLOYEE with invalid country"
	@Test
	public void testEditEmployee10() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.editEmployee("444.555.666-00", "func@func.com", "Avenida das Laranjas", "Laranjeiras",
					"Suco de Laranja", "D", "stock", 1800);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo PAÍS não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "EDIT EMPLOYEE with invalid job"
	@Test
	public void testEditEmployee11() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.editEmployee("444.555.666-00", "func@func.com", "Avenida das Laranjas", "Laranjeiras",
					"Suco de Laranja", "Doce de Laranja", "lula", 1800);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo CARGO não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "EDIT EMPLOYEE with invalid salary"
	@Test
	public void testEditEmployee12() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.editEmployee("444.555.666-00", "func@func.com", "Avenida das Laranjas", "Laranjeiras",
					"Suco de Laranja", "Doce de Laranja", "stock", 0);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo SALÁRIO não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "EDIT EMPLOYEE with negative salary"
	@Test
	public void testEditEmployee13() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.editEmployee("444.555.666-00", "func@func.com", "Avenida das Laranjas", "Laranjeiras",
					"Suco de Laranja", "Doce de Laranja", "stock", -10);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo SALÁRIO não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "EDIT EMPLOYEE with low salary"
	@Test
	public void testEditEmployee14() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.editEmployee("444.555.666-00", "func@func.com", "Avenida das Laranjas", "Laranjeiras",
					"Suco de Laranja", "Doce de Laranja", "stock", 100);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("O campo SALÁRIO não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "EDIT EMPLOYEE with multiple invalid fields"
	@Test
	public void testEditEmployee15() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		try {
			this.facade.editEmployee("4", "f", "A", "L", "S", "D", "s", 100);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade EDIT EMPLOYEE: " + e.getMessage());
			Assert.assertEquals(
					"Os campos CPF, EMAIL, RUA, CIDADE, ESTADO, PAÍS, CARGO, SALÁRIO não foram inseridos de forma correta.",
					e.getMessage());
		}
	}
}