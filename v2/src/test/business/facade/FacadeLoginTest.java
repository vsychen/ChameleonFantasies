package test.business.facade;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.NotFirstAccountException;
import main.business.facade.Facade;
import main.data.database.CustomerDatabase;
import main.data.database.EmployeeDatabase;
import main.data.database.FantasyDatabase;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the LOGIN method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeLoginTest {
	private Facade facade;
	private String password;

	@Before
	public void setUp() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException {
		this.facade = Facade.getInstance();
		this.password = this.facade.firstAccess("Marcos da Silva", "222.222.222-22", "funcionario@ufncoinairo.com",
				"Avenida", "Cidade", "Estado", "Pais", 3000);
	}

	@After
	public void tearDown() throws LoginErrorException, DatabaseErrorException {
		new CustomerDatabase().removeAll();
		new EmployeeDatabase().removeAll();
		new FantasyDatabase().removeAll();

		this.facade.close();
	}

	// Test "LOGIN in the system"
	@Test
	public void testLogin01() throws LoginErrorException, InvalidFieldException {
		this.facade.login("222.222.222-22", this.password);

		Assert.assertEquals(true, Facade.isLogged());
	}

	// Test "LOGIN in a system which already has an account logged in"
	@Test
	public void testLogin02() throws LoginErrorException, InvalidFieldException {
		this.facade.login("222.222.222-22", this.password);

		try {
			this.facade.login("222.222.222-22", this.password);
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade LOGIN: " + e.getMessage());
			Assert.assertEquals("Já há uma conta logada no sistema.", e.getMessage());
		}
	}

	// Test "LOGIN in a system with an invalid cpf"
	@Test
	public void testLogin03() throws LoginErrorException {
		try {
			this.facade.login("2", this.password);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade LOGIN: " + e.getMessage());
			Assert.assertEquals("O campo CPF não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "LOGIN in a system with an invalid password"
	@Test
	public void testLogin04() throws LoginErrorException {
		try {
			this.facade.login("222.222.222-22", "asb");
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade LOGIN: " + e.getMessage());
			Assert.assertEquals("O campo SENHA não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "LOGIN in a system with multiple invalid fields"
	@Test
	public void testLogin05() throws LoginErrorException {
		try {
			this.facade.login("2", "asb");
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade LOGIN: " + e.getMessage());
			Assert.assertEquals("Os campos CPF, SENHA não foram inseridos de forma correta.", e.getMessage());
		}
	}

	// Test "LOGIN in a system with unmatched cpf/password"
	@Test
	public void testLogin06() throws InvalidFieldException {
		try {
			this.facade.login("111.111.111-11", this.password);
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade LOGIN: " + e.getMessage());
			Assert.assertEquals("O cpf e/ou a senha foi digitada incorretamente.", e.getMessage());
		}
	}

	// Test "LOGIN in a system with unmatched cpf/password"
	@Test
	public void testLogin07() throws InvalidFieldException {
		try {
			this.facade.login("222.222.222-22", "asba");
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade LOGIN: " + e.getMessage());
			Assert.assertEquals("O cpf e/ou a senha foi digitada incorretamente.", e.getMessage());
		}
	}
}