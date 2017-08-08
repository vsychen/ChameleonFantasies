package test.business.control.customerControl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.control.CustomerControl;
import main.data.database.CustomerDatabase;
import main.data.entities.Customer;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the ADD and SEARCH methods in CustomerControl.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class CustomerControlAddSearchTest {
	private CustomerControl cc;

	@Before
	public void setUp() {
		this.cc = new CustomerControl(new CustomerDatabase());
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		(new CustomerDatabase()).removeAll();

		this.cc = null;
	}

	// Test "simple ADD and SEARCH in CustomerDatabase"
	@Test
	public void testAddSearch01() throws DatabaseErrorException {
		this.cc.addCustomer("José Araújo", "111.222.333-00");

		Assert.assertEquals(new Customer("José Araújo", "111.222.333-00").toString(),
				this.cc.searchCustomer("111.222.333-00").toString());
	}

	// Test "ADD and SEARCH in CustomerDatabase with multiple customers"
	@Test
	public void testAddSearch02() throws DatabaseErrorException {
		this.cc.addCustomer("José Araújo", "111.222.333-00");
		this.cc.addCustomer("Mayara Amaral", "222.333.444-00");

		Assert.assertEquals(new Customer("Mayara Amaral", "222.333.444-00").toString(),
				this.cc.searchCustomer("222.333.444-00").toString());
	}

	// Test "ADD duplicated customer in CustomerDatabase"
	@Test
	public void testAdd01() throws DatabaseErrorException {
		this.cc.addCustomer("José Araújo", "111.222.333-00");

		try {
			this.cc.addCustomer("José Araújo", "111.222.333-00");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - CustomerControl ADD: " + e.getMessage());
			Assert.assertEquals("Item já existe na base de dados.", e.getMessage());
		}
	}

	// Test "SEARCH for customer inexistent in CustomerDatabase"
	@Test
	public void testSearch01() {
		try {
			this.cc.searchCustomer("111.222.333-00");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - CustomerControl SEARCH: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}
}