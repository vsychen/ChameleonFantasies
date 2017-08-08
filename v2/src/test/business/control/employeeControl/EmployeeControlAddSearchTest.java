package test.business.control.employeeControl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.control.EmployeeControl;
import main.business.exceptions.NotFirstAccountException;
import main.data.database.EmployeeDatabase;
import main.data.entities.Address;
import main.data.entities.Employee;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the ADD and SEARCH methods in EmployeeControl.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class EmployeeControlAddSearchTest {
	private EmployeeControl ec;

	@Before
	public void setUp() {
		this.ec = new EmployeeControl(new EmployeeDatabase());
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		(new EmployeeDatabase()).removeAll();
		this.ec = null;
	}

	// Test "FIRST ACCESS in database"
	@Test
	public void testFirstAccess01() throws DatabaseErrorException, NotFirstAccountException {
		this.ec.firstAccess("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", 3000);

		Assert.assertEquals(
				new Employee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com",
						new Address("Avenida", "Cidade", "Estado", "Pais"), "admin", 3000).toString(),
				this.ec.searchEmployee("444.555.666-00").toString());
	}

	// Test "Not FIRST ACCESS in database"
	@Test
	public void testFirstAccess02() throws DatabaseErrorException, NotFirstAccountException {
		this.ec.firstAccess("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", 3000);

		try {
			this.ec.firstAccess("Silvia García", "444.444.444-44", "funcionario@ufncoinairo.com", "Avenida", "Cidade",
					"Estado", "Pais", 3000);
		} catch (NotFirstAccountException e) {
			System.out.println("NotFirstAccountException - EmployeeControl FIRST ACCESS: " + e.getMessage());
			Assert.assertEquals("Já há pelo menos uma conta cadastrada no sistema.", e.getMessage());
		}
	}

	// Test "simple ADD and SEARCH in EmployeeDatabase"
	@Test
	public void testAddSearch01() throws DatabaseErrorException {
		this.ec.addEmployee("Silvia García", "444.444.444-44", "funcionario@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "admin", 3000);
		Employee e = new Employee("Silvia García", "444.444.444-44", "funcionario@ufncoinairo.com",
				new Address("Avenida", "Cidade", "Estado", "Pais"), "admin", 3000);

		Assert.assertEquals(e.toString(), this.ec.searchEmployee("444.444.444-44").toString());
	}

	// Test "ADD and SEARCH in EmployeeDatabase with multiple employees"
	@Test
	public void testAddSearch02() throws DatabaseErrorException {
		this.ec.addEmployee("Silvia García", "444.444.444-44", "funcionario@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "admin", 3000);
		this.ec.addEmployee("Marcos do Vale", "456.456.456-00", "funcionario1@funcionario.com", "Avenida", "Cidade",
				"Estado", "Pais", "stock", 1500);
		Employee e = new Employee("Silvia García", "444.444.444-44", "funcionario@ufncoinairo.com",
				new Address("Avenida", "Cidade", "Estado", "Pais"), "admin", 3000);

		Assert.assertEquals(e.toString(), this.ec.searchEmployee("444.444.444-44").toString());
	}

	// Test "ADD duplicated employee in EmployeeDatabase"
	@Test
	public void testAdd01() throws DatabaseErrorException {
		this.ec.addEmployee("Silvia García", "444.444.444-44", "funcionario@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "admin", 3000);

		try {
			this.ec.addEmployee("Silvia García", "444.444.444-44", "funcionario@ufncoinairo.com", "Avenida", "Cidade",
					"Estado", "Pais", "admin", 3000);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - EmployeeControl ADD: " + e.getMessage());
			Assert.assertEquals("Item já existe na base de dados.", e.getMessage());
		}
	}

	// Test "SEARCH for employee inexistent in EmployeeDatabase"
	@Test
	public void testSearch01() {
		try {
			this.ec.searchEmployee("444.444.444-44");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - EmployeeControl SEARCH: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}
}