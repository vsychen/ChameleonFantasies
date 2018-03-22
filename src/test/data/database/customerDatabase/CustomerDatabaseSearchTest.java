package test.data.database.customerDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.data.database.CustomerDatabase;
import main.data.entities.Customer;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the SEARCH method in CustomerDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class CustomerDatabaseSearchTest {
	private CustomerDatabase cd;

	@Before
	public void setUp() throws DatabaseErrorException {
		this.cd = new CustomerDatabase();
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		if (!this.cd.isEmpty())
			this.cd.removeAll();

		this.cd = null;
	}

	// Test "SEARCH database with one element"
	@Test
	public void testSearch01() throws DatabaseErrorException {
		Customer c = new Customer("Julio dos Santos", "111.222.333-00");
		this.cd.add(c);

		Assert.assertEquals(c.toString(), this.cd.search("111.222.333-00").toString());
	}

	// Test "SEARCH database with multiple elements"
	@Test
	public void testSearch02() throws DatabaseErrorException {
		Customer c1 = new Customer("Julio dos Santos", "111.222.333-00");
		Customer c2 = new Customer("Carlos da Silva", "444.555.666-00");
		Customer c3 = new Customer("Pedro Melo", "777.888.999-00");
		Customer c4 = new Customer("Silvia Pereira", "333.222.111-00");

		this.cd.add(c1);
		this.cd.add(c2);
		this.cd.add(c3);
		this.cd.add(c4);

		Assert.assertEquals(c1.toString(), this.cd.search("111.222.333-00").toString());
	}

	// Test "SEARCH database with multiple elements"
	@Test
	public void testSearch03() throws DatabaseErrorException {
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

	// Test "SEARCH database with multiple elements"
	@Test
	public void testSearch04() throws DatabaseErrorException {
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

	// Test "SEARCH inexistent element in database"
	@Test
	public void testSearch05() {
		try {
			this.cd.search("777.888.999-00");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - CustomerDatabase SEARCH: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}
}