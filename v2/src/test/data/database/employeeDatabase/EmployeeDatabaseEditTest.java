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
 * Tests for the EDIT method in EmployeeDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class EmployeeDatabaseEditTest {
	private EmployeeDatabase ed;

	@Before
	public void setUp() throws DatabaseErrorException {
		this.ed = new EmployeeDatabase();

		Address a = new Address("Rua", "Cidade", "Estado", "Pais");
		this.ed.add(
				new Employee("Marcos da Silva", "111.222.333-00", "funcionario@ufncoinairo.com", a, "cashier", 1000));
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		if (!this.ed.isEmpty())
			this.ed.removeAll();

		this.ed = null;
	}

	// Test "EDIT one element of database with one element"
	@Test
	public void testEdit01() throws DatabaseErrorException {
		Employee e = this.ed.search("111.222.333-00");
		e.setEmail("funcionario@funcionario.com");
		this.ed.edit("111.222.333-00", e);

		Assert.assertEquals(e.toString(), this.ed.search("111.222.333-00").toString());
	}

	// Test "EDIT one element of database with multiple elements"
	@Test
	public void testEdit02() throws DatabaseErrorException {
		Address a = new Address("Rua", "Cidade", "Estado", "Pais");
		this.ed.add(
				new Employee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com", a, "cashier", 1000));

		Employee e = this.ed.search("111.222.333-00");
		e.setEmail("funcionario@funcionario.com");
		this.ed.edit("111.222.333-00", e);

		Assert.assertEquals(e.toString(), this.ed.search("111.222.333-00").toString());
	}

	// Test "EDIT removed element"
	@Test
	public void testEdit03() throws DatabaseErrorException {
		Employee e = this.ed.search("111.222.333-00");
		this.ed.removeAll();
		e.setEmail("funcionario@funcionario.com");

		try {
			this.ed.edit("111.222.333-00", e);
		} catch (DatabaseErrorException dee) {
			System.out.println("DatabaseErrorException - EmployeeDatabase EDIT: " + dee.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", dee.getMessage());
		}
	}

	// Test "EDIT element inexistent in database"
	@Test
	public void testEdit04() {
		Address a = new Address("Rua", "Cidade", "Estado", "Pais");
		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com", a, "cashier",
				1000);

		try {
			this.ed.edit("444.555.666-00", e);
		} catch (DatabaseErrorException dee) {
			System.out.println("DatabaseErrorException - EmployeeDatabase EDIT: " + dee.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", dee.getMessage());
		}
	}
}