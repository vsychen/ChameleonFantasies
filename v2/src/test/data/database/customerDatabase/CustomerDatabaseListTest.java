package test.data.database.customerDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.data.database.CustomerDatabase;
import main.data.entities.Customer;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the LIST method in CustomerDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class CustomerDatabaseListTest {
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

	// Test "LIST empty database"
	@Test
	public void testList01() throws DatabaseErrorException {
		Assert.assertEquals("", this.cd.list());
	}

	// Test "LIST database with one element"
	@Test
	public void testList02() throws DatabaseErrorException {
		Customer c = new Customer("Julio dos Santos", "111.222.333-00");
		this.cd.add(c);

		Assert.assertEquals(c.toString() + "\n", this.cd.list());
	}

	// Test "LIST database with multiple elements"
	@Test
	public void testList03() throws DatabaseErrorException {
		Customer c1 = new Customer("Julio dos Santos", "111.222.333-00");
		Customer c2 = new Customer("Carlos da Silva", "444.555.666-00");
		Customer c3 = new Customer("Pedro Melo", "777.888.999-00");

		this.cd.add(c1);
		this.cd.add(c2);
		this.cd.add(c3);

		Assert.assertEquals(c1.toString() + "\n" + c2.toString() + "\n" + c3.toString() + "\n", this.cd.list());
	}

	// Test "LIST database with multiple elements"
	@Test
	public void testList04() throws DatabaseErrorException {
		Customer c1 = new Customer("Pedro Melo", "777.888.999-00");
		Customer c2 = new Customer("Carlos da Silva", "444.555.666-00");
		Customer c3 = new Customer("Julio dos Santos", "111.222.333-00");

		this.cd.add(c1);
		this.cd.add(c2);
		this.cd.add(c3);

		Assert.assertEquals(c3.toString() + "\n" + c2.toString() + "\n" + c1.toString() + "\n", this.cd.list());
	}
}