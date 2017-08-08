package test.business.control.accessControl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.control.AccessControl;
import main.business.exceptions.LoginErrorException;
import main.data.database.CustomerDatabase;
import main.data.database.EmployeeDatabase;
import main.data.database.FantasyDatabase;
import main.data.entities.Address;
import main.data.entities.Cloth;
import main.data.entities.Customer;
import main.data.entities.Employee;
import main.data.entities.Fantasy;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the REMOVE method in AccessControl.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class AccessControlRemoveTest {
	private AccessControl ac;
	private CustomerDatabase cd;
	private EmployeeDatabase ed;
	private FantasyDatabase fd;

	@Before
	public void setUp() throws DatabaseErrorException {
		this.cd = new CustomerDatabase();
		this.ed = new EmployeeDatabase();
		this.fd = new FantasyDatabase();
		this.ac = new AccessControl(this.cd, this.ed, this.fd);

		this.cd.add(new Customer("José Araújo", "111.222.333-00"));
		this.ed.add(new Employee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com",
				new Address("Avenida", "Cidade", "Estado", "Pais"), "admin", 3000));
		this.fd.add(new Fantasy("Hulk", "1", null, null, new Cloth("Calça", "Roxa", false, false), null, null, 20, 100,
				300));
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		(new CustomerDatabase()).removeAll();
		(new EmployeeDatabase()).removeAll();
		(new FantasyDatabase()).removeAll();

		this.ac = null;
	}

	// Test "REMOVE one customer from database"
	@Test
	public void testRemove01() throws DatabaseErrorException {
		this.ac.remove("111.222.333-00", 0);

		try {
			this.cd.search("111.222.333-00");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - AccessControl REMOVE CUSTOMER: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}

	// Test "REMOVE one employee from database"
	@Test
	public void testRemove02() throws DatabaseErrorException {
		this.ac.remove("444.555.666-00", 1);

		try {
			this.cd.search("444.555.666-00");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - AccessControl REMOVE EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}

	// Test "REMOVE one fantasy from database"
	@Test
	public void testRemove03() throws DatabaseErrorException {
		this.ac.remove("1", 2);

		try {
			this.cd.search("1");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - AccessControl REMOVE FANTASY: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}

	// Test "REMOVE customer inexistent in database"
	@Test
	public void testRemove04() throws DatabaseErrorException {
		this.ac.remove("111.222.333-00", 0);

		try {
			this.ac.remove("111.222.333-00", 0);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - AccessControl REMOVE CUSTOMER: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}

	// Test "REMOVE employee inexistent in database"
	@Test
	public void testRemove05() throws DatabaseErrorException {
		this.ac.remove("444.555.666-00", 1);

		try {
			this.ac.remove("444.555.666-00", 1);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - AccessControl REMOVE EMPLOYEE: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}

	// Test "REMOVE fantasy inexistent in database"
	@Test
	public void testRemove06() throws DatabaseErrorException {
		this.ac.remove("1", 2);

		try {
			this.ac.remove("1", 2);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - AccessControl REMOVE FANTASY: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}

	// Test "REMOVE one customer from database with multiple customers"
	@Test
	public void testRemove07() throws DatabaseErrorException {
		this.cd.add(new Customer("José Araújo", "111.111.111-11"));
		this.ac.remove("111.111.111-11", 0);

		Assert.assertEquals(false, this.cd.isEmpty());
	}

	// Test "REMOVE one employee from database with multiple employees"
	@Test
	public void testRemove08() throws DatabaseErrorException {
		this.ed.add(new Employee("Amanda Morais", "444.444.444-44", "funcionario2@ufncoinairo.com",
				new Address("Avenida", "Cidade", "Estado", "Pais"), "cashier", 3000));
		this.ac.remove("444.444.444-44", 1);

		Assert.assertEquals(false, this.ed.isEmpty());
	}

	// Test "REMOVE one fantasy from database with multiple fantasies"
	@Test
	public void testRemove09() throws DatabaseErrorException {
		this.fd.add(new Fantasy("Batman", "2", new Cloth("Máscara", "Preta", false, false),
				new Cloth("Suéter", "Cinza", false, true), new Cloth("Calça", "Roxa", false, false),
				new Cloth("Luva", "Preta", false, false), new Cloth("Bota", "Preta", false, false), 10, 400, 800));
		this.ac.remove("2", 2);

		Assert.assertEquals(false, this.fd.isEmpty());
	}
}