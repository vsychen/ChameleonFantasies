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
 * Tests for the ADD method in EmployeeDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class EmployeeDatabaseAddTest {
	private EmployeeDatabase ed;

	@Before
	public void setUp() {
		this.ed = new EmployeeDatabase();
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		if (!this.ed.isEmpty())
			this.ed.removeAll();

		this.ed = null;
	}

	// Test "no ADD"
	@Test
	public void testAdd01() throws DatabaseErrorException {
		this.ed.removeAll();

		Assert.assertEquals(true, this.ed.isEmpty());
	}

	// Test "ADD one element to the database"
	@Test
	public void testAdd02() throws DatabaseErrorException {
		this.ed.removeAll();
		Address a = new Address("Rua", "Cidade", "Estado", "Pais");
		this.ed.add(
				new Employee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com", a, "cashier", 1000));

		Assert.assertEquals(false, this.ed.isEmpty());
	}

	// Test "ADD multiple elements to the database"
	@Test
	public void testAdd03() throws DatabaseErrorException {
		Address a1 = new Address("Rua1", "Cidade1", "Estado1", "Pais1");
		Employee e1 = new Employee("Marcos da Silva", "444.555.666-00", "funcionario1@ufncoinairo.com", a1, "stock",
				1000);
		Address a2 = new Address("Rua2", "Cidade2", "Estado2", "Pais2");
		Employee e2 = new Employee("Luana Lemos", "111.222.333-00", "funcionario2@ufncoinairo.com", a2, "cashier",
				1200);

		this.ed.add(e1);
		this.ed.add(e2);

		Assert.assertEquals(e1.toString(), this.ed.search("444.555.666-00").toString());
	}

	// Test "ADD multiple elements to the database"
	@Test
	public void testAdd04() throws DatabaseErrorException {
		Address a1 = new Address("Rua1", "Cidade1", "Estado1", "Pais1");
		Employee e1 = new Employee("Marcos da Silva", "444.555.666-00", "funcionario1@ufncoinairo.com", a1, "stock",
				1000);
		Address a2 = new Address("Rua2", "Cidade2", "Estado2", "Pais2");
		Employee e2 = new Employee("Luana Lemos", "111.222.333-00", "funcionario2@ufncoinairo.com", a2, "cashier",
				1200);

		this.ed.add(e1);
		this.ed.add(e2);

		Assert.assertEquals(e2.toString(), this.ed.search("111.222.333-00").toString());
	}

	// Test "ADD duplicated element to the database"
	@Test
	public void testAdd05() throws DatabaseErrorException {
		Address a = new Address("Rua1", "Cidade1", "Estado1", "Pais1");
		Employee e = new Employee("Marcos da Silva", "111.222.333-00", "funcionario1@ufncoinairo.com", a, "stock",
				1000);
		this.ed.add(e);

		try {
			this.ed.add(e);
		} catch (DatabaseErrorException dee) {
			System.out.println("DatabaseErrorException - EmployeeDatabase ADD: " + dee.getMessage());
			Assert.assertEquals("Item já existe na base de dados.", dee.getMessage());
		}
	}
}