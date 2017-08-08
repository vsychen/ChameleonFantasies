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
 * Tests for the LOGOUT method in AccessControl.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class AccessControlLogoutTest {
	private AccessControl ac;
	private Session s;

	@Before
	public void setUp() throws DatabaseErrorException, LoginErrorException {
		this.ac = new AccessControl(new CustomerDatabase(), new EmployeeDatabase(), new FantasyDatabase());

		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com",
				new Address("Avenida", "Cidade", "Estado", "Pais"), "admin", 3000);
		new EmployeeDatabase().add(e);

		this.s = this.ac.login("444.555.666-00", e.getPassword());
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		(new EmployeeDatabase()).removeAll();

		this.ac = null;
	}

	// Test "LOGOUT"
	@Test
	public void testLogout01() {
		this.s = this.ac.logout();

		Assert.assertEquals(null, s);
	}
}