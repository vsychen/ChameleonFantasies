package test.data.database.customerDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.data.database.CustomerDatabase;
import main.data.entities.Customer;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the REMOVE method in CustomerDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class CustomerDatabaseRemoveTest {
	private CustomerDatabase cd;

	@Before
	public void setUp() throws DatabaseErrorException {
		this.cd = new CustomerDatabase();
		this.cd.add(new Customer("Julio dos Santos", "111.222.333-00"));
		this.cd.add(new Customer("Carlos da Silva", "444.555.666-00"));
		this.cd.add(new Customer("Pedro Melo", "777.888.999-00"));
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		if (!this.cd.isEmpty())
			this.cd.removeAll();

		this.cd = null;
	}

	// Test "REMOVE from database with multiple elements"
	@Test
	public void testRemove01() throws DatabaseErrorException {
		this.cd.remove("111.222.333-00");

		Customer c2 = this.cd.search("444.555.666-00");
		Customer c3 = this.cd.search("777.888.999-00");

		Assert.assertEquals(c2.toString() + "\n" + c3.toString() + "\n", this.cd.list());
	}

	// Test "REMOVE multiple elements from database with multiple elements"
	@Test
	public void testRemove02() throws DatabaseErrorException {
		this.cd.remove("111.222.333-00");
		this.cd.remove("444.555.666-00");

		Customer c = this.cd.search("777.888.999-00");

		Assert.assertEquals(c.toString() + "\n", this.cd.list());
	}

	// Test "REMOVE ALL elements from database with multiple elements"
	@Test
	public void testRemove03() throws DatabaseErrorException {
		this.cd.removeAll();

		Assert.assertEquals(true, this.cd.isEmpty());
	}

	// Test "REMOVE element from empty database"
	@Test
	public void testRemove04() throws DatabaseErrorException {
		this.cd.removeAll();

		try {
			this.cd.remove("111.222.333-00");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - CustomerDatabase REMOVE: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}

	// Test "REMOVE element inexistent in database"
	@Test
	public void testRemove05() throws DatabaseErrorException {
		this.cd.remove("111.222.333-00");

		try {
			this.cd.remove("111.222.333-00");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - CustomerDatabase REMOVE: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}
}