package br.com.chameleonfantasies.model.database;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.chameleonfantasies.model.entities.Address;
import br.com.chameleonfantasies.model.entities.Employee;

public class EmployeeSQLTest {
	private EmployeeSQL esql;

	@Before
	public void setUp() {
		this.esql = new EmployeeSQL();
		this.esql.setUser("cf").setPass("1234").setPathToConnect("chameleonfantasies");
	}

	@After
	public void tearDown() throws SQLException {
		this.esql.removeAll();
	}

	@Test
	public void testEmployeeSQL01() throws SQLException {
		Employee e1 = new Employee("a", "1", "a@a", new Address("a", "a", "a", "a"), "a", 1000);
		Employee e2 = new Employee("a", "2", "a@a.com", new Address("ab", "ab", "ab", "ab"), "c", 3000);

		Assert.assertEquals(0, this.esql.list().size());
		this.esql.insert(e1);
		Assert.assertEquals(e1.toString(), this.esql.searchByCpf(e1.getCpf()).toString());

		e1.setEmail("safoihasf@asogihasog.com");
		this.esql.update(e1);
		Assert.assertEquals("safoihasf@asogihasog.com", esql.searchById(e1.getId()).getEmail());
		Assert.assertNotEquals(0, this.esql.list().size());

		this.esql.insert(e2);
		Assert.assertEquals(2, this.esql.searchByName(e2.getName()).size());

		this.esql.remove(e1.getId());
		this.esql.remove(e2.getId());
		Assert.assertEquals(0, this.esql.list().size());
	}

	@Test
	public void testEmployeeSQL02() throws SQLException {
		Employee e1 = new Employee("a", "1", "a@a", new Address("a", "a", "a", "a"), "a", 1000);
		Employee e2 = new Employee("b", "2", "a@a.com", new Address("ab", "ab", "ab", "ab"), "c", 3000);

		this.esql.insert(e1);
		this.esql.insert(e2);
		Assert.assertEquals(1, this.esql.searchByName(e2.getName()).size());
		Assert.assertEquals(2, this.esql.removeAll());
	}
}