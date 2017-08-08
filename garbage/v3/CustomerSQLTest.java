package br.com.chameleonfantasies.model.database;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.chameleonfantasies.model.entities.Customer;

public class CustomerSQLTest {
	private CustomerSQL csql;

	@Before
	public void setUp() {
		this.csql = new CustomerSQL();
		this.csql.setUser("cf").setPass("1234").setPathToConnect("chameleonfantasies");
	}

	@After
	public void tearDown() throws SQLException {
		this.csql.removeAll();
	}

	@Test
	public void testCustomerSQL01() throws SQLException {
		Customer c1 = new Customer("a", "1", 0);
		Customer c2 = new Customer("a", "2", 20);

		Assert.assertEquals(0, this.csql.list().size());
		this.csql.insert(c1);
		Assert.assertEquals(0, this.csql.searchByCpf(c1.getCpf()).getSpending(), 0);

		c1.setSpending(100);
		csql.update(c1);
		Assert.assertEquals(100, csql.searchById(c1.getId()).getSpending(), 0);
		Assert.assertNotEquals(0, this.csql.list().size());

		this.csql.insert(c2);
		Assert.assertEquals(2, this.csql.searchByName(c2.getName()).size());

		this.csql.remove(c1.getId());
		this.csql.remove(c2.getId());
		Assert.assertEquals(0, this.csql.list().size());
	}

	@Test
	public void testCustomerSQL02() throws SQLException {
		Customer c1 = new Customer("a", "1", 100);
		Customer c2 = new Customer("b", "2", 300);

		this.csql.insert(c1);
		this.csql.insert(c2);
		Assert.assertEquals(1, this.csql.searchByName(c2.getName()).size());
		Assert.assertEquals(2, this.csql.removeAll());
	}
}