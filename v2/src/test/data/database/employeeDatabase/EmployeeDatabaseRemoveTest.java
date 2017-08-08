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
 * Tests for the REMOVE method in EmployeeDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class EmployeeDatabaseRemoveTest {
	private EmployeeDatabase ed;

	@Before
	public void setUp() throws DatabaseErrorException {
		this.ed = new EmployeeDatabase();
		Address a1 = new Address("Rua1", "Cidade1", "Estado1", "Pais1");
		Address a2 = new Address("Rua2", "Cidade2", "Estado2", "Pais2");
		Address a3 = new Address("Rua3", "Cidade3", "Estado3", "Pais3");

		this.ed.add(
				new Employee("Marcos da Silva", "444.555.666-00", "funcionario1@ufncoinairo.com", a1, "stock", 1000));
		this.ed.add(new Employee("Luana Lemos", "111.222.333-00", "funcionario2@ufncoinairo.com", a2, "cashier", 1200));
		this.ed.add(new Employee("Bruno Aguiar", "777.888.999-00", "funcionario3@ufncoinairo.com", a3, "stock", 1000));
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		if (!this.ed.isEmpty())
			this.ed.removeAll();

		this.ed = null;
	}

	// Test "REMOVE from database with multiple elements"
	@Test
	public void testRemove01() throws DatabaseErrorException {
		this.ed.remove("111.222.333-00");

		Employee e2 = this.ed.search("444.555.666-00");
		Employee e3 = this.ed.search("777.888.999-00");

		Assert.assertEquals(e2.toString() + "\n" + e3.toString() + "\n", this.ed.list());
	}

	// Test "REMOVE multiple elements from database with multiple elements"
	@Test
	public void testRemove02() throws DatabaseErrorException {
		this.ed.remove("111.222.333-00");
		this.ed.remove("444.555.666-00");

		Employee e = this.ed.search("777.888.999-00");

		Assert.assertEquals(e.toString() + "\n", this.ed.list());
	}

	// Test "REMOVE ALL elements from database with multiple elements"
	@Test
	public void testRemove03() throws DatabaseErrorException {
		this.ed.removeAll();

		Assert.assertEquals(true, this.ed.isEmpty());
	}

	// Test "REMOVE element from empty database"
	@Test
	public void testRemove04() throws DatabaseErrorException {
		this.ed.removeAll();

		try {
			this.ed.remove("111.222.333-00");
		} catch (DatabaseErrorException dee) {
			System.out.println("DatabaseErrorException - EmployeeDatabase REMOVE: " + dee.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", dee.getMessage());
		}
	}

	// Test "REMOVE element inexistent in database"
	@Test
	public void testRemove05() throws DatabaseErrorException {
		this.ed.remove("111.222.333-00");

		try {
			this.ed.remove("111.222.333-00");
		} catch (DatabaseErrorException dee) {
			System.out.println("DatabaseErrorException - EmployeeDatabase REMOVE: " + dee.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", dee.getMessage());
		}
	}
}