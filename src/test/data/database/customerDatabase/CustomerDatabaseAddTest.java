package test.data.database.customerDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.data.database.CustomerDatabase;
import main.data.entities.Customer;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the ADD method in CustomerDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class CustomerDatabaseAddTest {
	private CustomerDatabase cd;

	@Before
	public void setUp() {
		this.cd = new CustomerDatabase();
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		if (!this.cd.isEmpty())
			this.cd.removeAll();

		this.cd = null;
	}

	// Test "no ADD"
	@Test
	public void testAdd01() throws DatabaseErrorException {
		this.cd.removeAll();

		Assert.assertEquals(true, this.cd.isEmpty());
	}

	// Test "ADD one element to the database"
	@Test
	public void testAdd02() throws DatabaseErrorException {
		this.cd.removeAll();
		this.cd.add(new Customer("Julio dos Santos", "111.222.333-00"));

		Assert.assertEquals(false, this.cd.isEmpty());
	}

	// Test "ADD multiple elements to the database"
	@Test
	public void testAdd03() throws DatabaseErrorException {
		Customer c1 = new Customer("Julio dos Santos", "111.222.333-00");
		Customer c2 = new Customer("Carlos da Silva", "444.555.666-00");
		Customer c3 = new Customer("Pedro Melo", "777.888.999-00");
		Customer c4 = new Customer("Silvia Pereira", "333.222.111-00");

		this.cd.add(c1);
		this.cd.add(c2);
		this.cd.add(c3);
		this.cd.add(c4);

		Assert.assertEquals(c3.toString(), this.cd.search("777.888.999-00").toString());
	}

	// Test "ADD multiple elements to the database"
	@Test
	public void testAdd04() throws DatabaseErrorException {
		Customer c1 = new Customer("Julio dos Santos", "111.222.333-00");
		Customer c2 = new Customer("Carlos da Silva", "444.555.666-00");
		Customer c3 = new Customer("Pedro Melo", "777.888.999-00");
		Customer c4 = new Customer("Silvia Pereira", "333.222.111-00");

		this.cd.add(c1);
		this.cd.add(c2);
		this.cd.add(c3);
		this.cd.add(c4);

		Assert.assertEquals(c4.toString(), this.cd.search("333.222.111-00").toString());
	}

	// Test "ADD duplicated element to the database"
	@Test
	public void testAdd05() throws DatabaseErrorException {
		this.cd.add(new Customer("Julio dos Santos", "111.222.333-00"));

		try {
			this.cd.add(new Customer("Julio dos Santos", "111.222.333-00"));
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - CustomerDatabase ADD: " + e.getMessage());
			Assert.assertEquals("Item já existe na base de dados.", e.getMessage());
		}
	}
}