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
 * Tests for the FIRST ACCESS method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeFirstAccessTest {
	private Facade facade;

	@Before
	public void setUp() {
		this.facade = Facade.getInstance();
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		new EmployeeDatabase().removeAll();

		this.facade.close();
	}

	// Test "FIRST ACCESS to the system"
	@Test
	public void testFirstAccess01() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException,
			LoginErrorException, UnauthorizedActionException {
		String password = this.facade.firstAccess("Julio Moreira", "123.123.123-11", "jm@algumemail.com",
				"Floresta Negra 1061", "Farol", "EitaUmEstado", "MinhaNossaUmPais", 4000);
		this.facade.login("123.123.123-11", password);

		Employee e = new Employee("Julio Moreira", "123.123.123-11", "jm@algumemail.com",
				new Address("Floresta Negra 1061", "Farol", "EitaUmEstado", "MinhaNossaUmPais"), "admin", 4000);

		Assert.assertEquals(e.toString(), this.facade.seeEmployee("123.123.123-11").toString());
	}

	// Test "not FIRST ACCESS to the system"
	@Test
	public void testFirstAccess02() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException {
		this.facade.firstAccess("Julio Moreira", "123.123.123-11", "jm@algumemail.com", "Floresta Negra 1061", "Farol",
				"EitaUmEstado", "MinhaNossaUmPais", 4000);

		try {
			this.facade.firstAccess("Vai Dar Errado", "111.111.111-11", "vde@algumemail.com", "Crash S/N", "Exception",
					"Error", "BOOM", 2000);
		} catch (NotFirstAccountException e) {
			System.out.println("NotFirstAccountException - Facade FIRST ACCESS: " + e.getMessage());
			Assert.assertEquals("Já há pelo menos uma conta cadastrada no sistema.", e.getMessage());
		}
	}

	// Test "FIRST ACCESS with invalid name"
	@Test
	public void testFirstAccess03() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException {
		try {
			this.facade.firstAccess("Al", "123.123.123-11", "jm@algumemail.com", "Floresta Negra 1061", "Farol",
					"EitaUmEstado", "MinhaNossaUmPais", 4000);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade FIRST ACCESS: " + e.getMessage());
			Assert.assertEquals("O campo NOME não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "FIRST ACCESS with invalid cpf"
	@Test
	public void testFirstAccess04() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException {
		try {
			this.facade.firstAccess("Julio Moreira", "1", "jm@algumemail.com", "Floresta Negra 1061", "Farol",
					"EitaUmEstado", "MinhaNossaUmPais", 4000);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade FIRST ACCESS: " + e.getMessage());
			Assert.assertEquals("O campo CPF não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "FIRST ACCESS with invalid email"
	@Test
	public void testFirstAccess05() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException {
		try {
			this.facade.firstAccess("Julio Moreira", "123.123.123-11", "jm", "Floresta Negra 1061", "Farol",
					"EitaUmEstado", "MinhaNossaUmPais", 4000);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade FIRST ACCESS: " + e.getMessage());
			Assert.assertEquals("O campo EMAIL não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "FIRST ACCESS with invalid street"
	@Test
	public void testFirstAccess06() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException {
		try {
			this.facade.firstAccess("Julio Moreira", "123.123.123-11", "jm@algumemail.com", "Rua", "Farol",
					"EitaUmEstado", "MinhaNossaUmPais", 4000);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade FIRST ACCESS: " + e.getMessage());
			Assert.assertEquals("O campo RUA não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "FIRST ACCESS with invalid city"
	@Test
	public void testFirstAccess07() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException {
		try {
			this.facade.firstAccess("Julio Moreira", "123.123.123-11", "jm@algumemail.com", "Floresta Negra 1061", "AS",
					"EitaUmEstado", "MinhaNossaUmPais", 4000);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade FIRST ACCESS: " + e.getMessage());
			Assert.assertEquals("O campo CIDADE não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "FIRST ACCESS with invalid state"
	@Test
	public void testFirstAccess08() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException {
		try {
			this.facade.firstAccess("Julio Moreira", "123.123.123-11", "jm@algumemail.com", "Floresta Negra 1061",
					"Farol", "E", "MinhaNossaUmPais", 4000);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade FIRST ACCESS: " + e.getMessage());
			Assert.assertEquals("O campo ESTADO não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "FIRST ACCESS with invalid country"
	@Test
	public void testFirstAccess09() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException {
		try {
			this.facade.firstAccess("Julio Moreira", "123.123.123-11", "jm@algumemail.com", "Floresta Negra 1061",
					"Farol", "EitaUmEstado", "a", 4000);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade FIRST ACCESS: " + e.getMessage());
			Assert.assertEquals("O campo PAÍS não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "FIRST ACCESS with invalid salary"
	@Test
	public void testFirstAccess10() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException {
		try {
			this.facade.firstAccess("Julio Moreira", "123.123.123-11", "jm@algumemail.com", "Floresta Negra 1061",
					"Farol", "EitaUmEstado", "MinhaNossaUmPais", -10);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade FIRST ACCESS: " + e.getMessage());
			Assert.assertEquals("O campo SALÁRIO não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "FIRST ACCESS with multiple invalid fields"
	@Test
	public void testFirstAccess11() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException {
		try {
			this.facade.firstAccess("J", "1", "j", "F", "F", "E", "M", -10);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade FIRST ACCESS: " + e.getMessage());
			Assert.assertEquals(
					"Os campos NOME, CPF, EMAIL, RUA, CIDADE, ESTADO, PAÍS, SALÁRIO não foram inseridos de forma correta.",
					e.getMessage());
		}
	}
}