package test.data.database.employeeDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.data.database.EmployeeDatabase;
import main.data.entities.Address;
import main.data.entities.Employee;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the SEARCH method in EmployeeDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class EmployeeDatabaseSearchTest {
	private EmployeeDatabase ed;

	@Before
	public void setUp() throws DatabaseErrorException {
		this.ed = new EmployeeDatabase();
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		if (!this.ed.isEmpty())
			this.ed.removeAll();

		this.ed = null;
	}

	// Test "SEARCH database with one element"
	@Test
	public void testSearch01() throws DatabaseErrorException {
		Address a = new Address("Rua1", "Cidade1", "Estado1", "Pais1");
		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario1@ufncoinairo.com", a, "stock",
				1000);
		this.ed.add(e);

		Assert.assertEquals(e.toString(), this.ed.search("444.555.666-00").toString());
	}

	// Test "SEARCH database with multiple elements"
	@Test
	public void testSearch02() throws DatabaseErrorException {
		Address a1 = new Address("Rua1", "Cidade1", "Estado1", "Pais1");
		Address a2 = new Address("Rua2", "Cidade2", "Estado2", "Pais2");
		Address a3 = new Address("Rua3", "Cidade3", "Estado3", "Pais3");

		Employee e1 = new Employee("Marcos da Silva", "444.555.666-00", "funcionario1@ufncoinairo.com", a1, "stock",
				1000);
		Employee e2 = new Employee("Luana Lemos", "111.222.333-00", "funcionario2@ufncoinairo.com", a2, "cashier",
				1200);
		Employee e3 = new Employee("Bruno Aguiar", "777.888.999-00", "funcionario3@ufncoinairo.com", a3, "stock", 1000);

		this.ed.add(e1);
		this.ed.add(e2);
		this.ed.add(e3);

		Assert.assertEquals(e2.toString(), this.ed.search("111.222.333-00").toString());
	}

	// Test "SEARCH database with multiple elements"
	@Test
	public void testSearch03() throws DatabaseErrorException {
		Address a1 = new Address("Rua1", "Cidade1", "Estado1", "Pais1");
		Address a2 = new Address("Rua2", "Cidade2", "Estado2", "Pais2");
		Address a3 = new Address("Rua3", "Cidade3", "Estado3", "Pais3");

		Employee e1 = new Employee("Marcos da Silva", "444.555.666-00", "funcionario1@ufncoinairo.com", a1, "stock",
				1000);
		Employee e2 = new Employee("Luana Lemos", "111.222.333-00", "funcionario2@ufncoinairo.com", a2, "cashier",
				1200);
		Employee e3 = new Employee("Bruno Aguiar", "777.888.999-00", "funcionario3@ufncoinairo.com", a3, "stock", 1000);

		this.ed.add(e1);
		this.ed.add(e2);
		this.ed.add(e3);

		Assert.assertEquals(e3.toString(), this.ed.search("777.888.999-00").toString());
	}

	// Test "SEARCH database with multiple elements"
	@Test
	public void testSearch04() throws DatabaseErrorException {
		Address a1 = new Address("Rua1", "Cidade1", "Estado1", "Pais1");
		Address a2 = new Address("Rua2", "Cidade2", "Estado2", "Pais2");
		Address a3 = new Address("Rua3", "Cidade3", "Estado3", "Pais3");

		Employee e1 = new Employee("Marcos da Silva", "444.555.666-00", "funcionario1@ufncoinairo.com", a1, "stock",
				1000);
		Employee e2 = new Employee("Luana Lemos", "111.222.333-00", "funcionario2@ufncoinairo.com", a2, "cashier",
				1200);
		Employee e3 = new Employee("Bruno Aguiar", "777.888.999-00", "funcionario3@ufncoinairo.com", a3, "stock", 1000);

		this.ed.add(e1);
		this.ed.add(e2);
		this.ed.add(e3);

		Assert.assertEquals(e1.toString(), this.ed.search("444.555.666-00").toString());
	}

	// Test "SEARCH inexistent element in database"
	@Test
	public void testSearch05() {
		try {
			this.ed.search("777.888.999-00");
		} catch (DatabaseErrorException dee) {
			System.out.println("DatabaseErrorException - EmployeeDatabase SEARCH: " + dee.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", dee.getMessage());
		}
	}
}