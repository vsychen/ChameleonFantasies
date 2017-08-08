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
 * Tests for the LIST method in EmployeeDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class EmployeeDatabaseListTest {
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

	// Test "LIST empty database"
	@Test
	public void testList01() throws DatabaseErrorException {
		Assert.assertEquals("", this.ed.list());
	}

	// Test "LIST database with one element"
	@Test
	public void testList02() throws DatabaseErrorException {
		Address a = new Address("Rua", "Cidade", "Estado", "Pais");
		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com", a, "cashier",
				1000);
		this.ed.add(e);

		Assert.assertEquals(e.toString() + "\n", this.ed.list());
	}

	// Test "LIST database with multiple elements"
	@Test
	public void testList03() throws DatabaseErrorException {
		Address a1 = new Address("Rua1", "Cidade1", "Estado1", "Pais1");
		Employee e1 = new Employee("Marcos da Silva", "444.555.666-00", "funcionario1@ufncoinairo.com", a1, "stock",
				1000);
		Address a2 = new Address("Rua2", "Cidade2", "Estado2", "Pais2");
		Employee e2 = new Employee("Luana Lemos", "111.222.333-00", "funcionario2@ufncoinairo.com", a2, "cashier",
				1200);
		Address a3 = new Address("Rua3", "Cidade3", "Estado3", "Pais3");
		Employee e3 = new Employee("Bruno Aguiar", "777.888.999-00", "funcionario3@ufncoinairo.com", a3, "stock", 1000);

		this.ed.add(e1);
		this.ed.add(e2);
		this.ed.add(e3);

		Assert.assertEquals(e2.toString() + "\n" + e1.toString() + "\n" + e3.toString() + "\n", this.ed.list());
	}
}