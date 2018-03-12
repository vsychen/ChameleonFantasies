package test.business.control.accessControl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.control.AccessControl;
import main.business.entities.Session;
import main.business.exceptions.LoginErrorException;
import main.data.database.CustomerDatabase;
import main.data.database.EmployeeDatabase;
import main.data.database.FantasyDatabase;
import main.data.entities.Address;
import main.data.entities.Employee;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the LOGIN method in AccessControl.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class AccessControlLoginTest {
	private AccessControl ac;
	private String password;

	@Before
	public void setUp() throws DatabaseErrorException {
		this.ac = new AccessControl(new CustomerDatabase(), new EmployeeDatabase(), new FantasyDatabase());

		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com",
				new Address("Avenida", "Cidade", "Estado", "Pais"), "admin", 3000);
		this.password = e.getPassword();
		new EmployeeDatabase().add(e);
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		(new EmployeeDatabase()).removeAll();

		this.ac = null;
	}

	// Test "simple LOGIN"
	@Test
	public void testLogin01() throws LoginErrorException {
		Session s = this.ac.login("444.555.666-00", this.password);

		Assert.assertNotEquals(null, s);
	}

	// Test "LOGIN with unmatched cpf/password"
	@Test
	public void testLogin02() {
		try {
			this.ac.login("111.222.333-00", this.password);
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - AccessControl LOGIN: " + e.getMessage());
			Assert.assertEquals("O cpf e/ou a senha foi digitada incorretamente.", e.getMessage());
		}
	}

	// Test "LOGIN with unmatched cpf/password"
	@Test
	public void testLogin03() throws LoginErrorException {
		try {
			this.ac.login("444.555.666-00", this.password + "A");
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - AccessControl LOGIN: " + e.getMessage());
			Assert.assertEquals("O cpf e/ou a senha foi digitada incorretamente.", e.getMessage());
		}
	}
}