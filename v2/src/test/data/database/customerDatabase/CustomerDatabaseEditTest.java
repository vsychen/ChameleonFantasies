package test.data.database.customerDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.data.database.CustomerDatabase;
import main.data.entities.Customer;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the EDIT method in CustomerDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class CustomerDatabaseEditTest {
	private CustomerDatabase cd;

	@Before
	public void setUp() throws DatabaseErrorException {
		this.cd = new CustomerDatabase();
		this.cd.add(new Customer("Julio dos Santos", "111.222.333-00"));
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		if (!this.cd.isEmpty())
			this.cd.removeAll();

		this.cd = null;
	}

	// Test "EDIT one element of database with one element"
	@Test
	public void testEdit01() throws DatabaseErrorException {
		Customer c = this.cd.search("111.222.333-00");
		c.setSpending(10);

		this.cd.edit("111.222.333-00", c);

		Assert.assertEquals(c.toString(), this.cd.search("111.222.333-00").toString());
	}

	// Test "EDIT one element of database with multiple elements"
	@Test
	public void testEdit02() throws DatabaseErrorException {
		this.cd.add(new Customer("Carlos da Silva", "444.555.666-00"));

		Customer c = this.cd.search("111.222.333-00");
		c.setSpending(10);

		this.cd.edit("111.222.333-00", c);

		Assert.assertEquals(c.toString(), this.cd.search("111.222.333-00").toString());
	}

	// Test "EDIT removed element"
	@Test
	public void testEdit03() throws DatabaseErrorException {
		Customer c = this.cd.search("111.222.333-00");
		this.cd.removeAll();
		c.setSpending(10);

		try {
			this.cd.edit("111.222.333-00", c);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - CustomerDatabase EDIT: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}

	// Test "EDIT element inexistent in database"
	@Test
	public void testEdit04() {
		Customer c = new Customer("Carlos da Silva", "444.555.666-00");
		c.setSpending(10);

		try {
			this.cd.edit("444.555.666-00", c);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - CustomerDatabase EDIT: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}
}