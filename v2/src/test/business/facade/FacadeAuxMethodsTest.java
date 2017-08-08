package test.business.facade;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.NotFirstAccountException;
import main.business.facade.Facade;
import main.data.database.EmployeeDatabase;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the auxiliary methods in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeAuxMethodsTest {

	@After
	public void tearDown() throws DatabaseErrorException {
		new EmployeeDatabase().removeAll();
	}

	@Test
	public void testGetInstance1() {
		Facade f = Facade.getInstance();

		Assert.assertNotEquals(null, f);
	}

	// Test "check if IS LOGGED IN"
	@Test
	public void testIsLogged01()
			throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException, LoginErrorException {
		Facade f = Facade.getInstance();
		String password = f.firstAccess("Marcos da Silva", "222.222.222-22", "funcionario@ufncoinairo.com", "Avenida",
				"Cidade", "Estado", "Pais", 3000);
		f.login("222.222.222-22", password);

		Assert.assertEquals(true, Facade.isLogged());
	}

	// Test "check if Facade is instanced NOT LOGGED IN"
	@SuppressWarnings("unused")
	@Test
	public void testIsLogged02() {
		Facade f = Facade.getInstance();

		Assert.assertEquals(false, Facade.isLogged());
	}

	// Test "check if IS NOT LOGGED after logout"
	@Test
	public void testIsLogged03()
			throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException, LoginErrorException {
		Facade f = Facade.getInstance();
		String password = f.firstAccess("Marcos da Silva", "222.222.222-22", "funcionario@ufncoinairo.com", "Avenida",
				"Cidade", "Estado", "Pais", 3000);
		f.login("222.222.222-22", password);
		f.logout();

		Assert.assertEquals(false, Facade.isLogged());
	}

	// Test "check if IS NOT LOGGED after CLOSE()"
	@Test
	public void testClose01()
			throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException, LoginErrorException {
		Facade f = Facade.getInstance();
		String password = f.firstAccess("Marcos da Silva", "222.222.222-22", "funcionario@ufncoinairo.com", "Avenida",
				"Cidade", "Estado", "Pais", 3000);
		f.login("222.222.222-22", password);
		f.close();

		Assert.assertEquals(false, Facade.isLogged());
	}
}