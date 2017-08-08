package test.business.facade;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.NotFirstAccountException;
import main.business.facade.Facade;
import main.data.database.EmployeeDatabase;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the LOGOUT method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeLogoutTest {
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
		new EmployeeDatabase().removeAll();

		this.facade.close();
	}

	// Test "LOGOUT from the system"
	@Test
	public void testLogout01() throws LoginErrorException, InvalidFieldException {
		this.facade.login("222.222.222-22", this.password);
		this.facade.logout();

		Assert.assertEquals(false, Facade.isLogged());
	}

	// Test "LOGOUT from a system which didn't logged in yet"
	@Test
	public void testLogin02() {
		try {
			this.facade.logout();
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade LOGOUT: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}

	// Test "LOGOUT from the system which already has logged out"
	@Test
	public void testLogin03() throws LoginErrorException, InvalidFieldException {
		this.facade.login("222.222.222-22", this.password);
		this.facade.logout();

		try {
			this.facade.logout();
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade LOGOUT: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}
}