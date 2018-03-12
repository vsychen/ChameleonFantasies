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
 * Tests for the EDIT method in EmployeeControl.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class EmployeeControlEditTest {
	private EmployeeControl ec;

	@Before
	public void setUp() throws DatabaseErrorException, NotFirstAccountException {
		this.ec = new EmployeeControl(new EmployeeDatabase());
		this.ec.firstAccess("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", 3000);
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		(new EmployeeDatabase()).removeAll();
		this.ec = null;
	}

	// Test "multiple simple EDIT in EmployeeDatabase with a single employee"
	@Test
	public void testEdit01() throws DatabaseErrorException {
		this.ec.editEmployee("444.555.666-00", "funcionario@funcionario.com", "", "", "", "", "", 0);
		this.ec.editEmployee("444.555.666-00", "", "Rua das Rosas", "", "", "", "", 0);
		this.ec.editEmployee("444.555.666-00", "", "", "Jardim", "", "", "", 0);
		this.ec.editEmployee("444.555.666-00", "", "", "", "Estufa", "", "", 0);
		this.ec.editEmployee("444.555.666-00", "", "", "", "", "Floricultura", "", 0);
		this.ec.editEmployee("444.555.666-00", "", "", "", "", "", "stock", 0);
		this.ec.editEmployee("444.555.666-00", "", "", "", "", "", "", 4000);

		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario@funcionario.com",
				new Address("Rua das Rosas", "Jardim", "Estufa", "Floricultura"), "stock", 4000);

		Assert.assertEquals(e.toString(), this.ec.searchEmployee("444.555.666-00").toString());
	}

	// Test "single EDIT in EmployeeDatabase with a single employee"
	@Test
	public void testEdit02() throws DatabaseErrorException {
		this.ec.editEmployee("444.555.666-00", "funcionario@funcionario.com", "Rua das Rosas", "Jardim", "Estufa",
				"Floricultura", "stock", 4000);
		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario@funcionario.com",
				new Address("Rua das Rosas", "Jardim", "Estufa", "Floricultura"), "stock", 4000);

		Assert.assertEquals(e.toString(), this.ec.searchEmployee("444.555.666-00").toString());
	}

	// Test "multiple simple EDIT in EmployeeDatabase with multiple employees"
	@Test
	public void testEdit03() throws DatabaseErrorException {
		this.ec.addEmployee("Joana D'Arc", "999.888.777-66", "jdc@jdc.com", "Avenida", "Cidade", "Estado", "Pais",
				"stock", 1300);

		this.ec.editEmployee("444.555.666-00", "funcionario@funcionario.com", "", "", "", "", "", 0);
		this.ec.editEmployee("444.555.666-00", "", "Rua das Rosas", "", "", "", "", 0);
		this.ec.editEmployee("444.555.666-00", "", "", "Jardim", "", "", "", 0);
		this.ec.editEmployee("444.555.666-00", "", "", "", "Estufa", "", "", 0);
		this.ec.editEmployee("444.555.666-00", "", "", "", "", "Floricultura", "", 0);
		this.ec.editEmployee("444.555.666-00", "", "", "", "", "", "stock", 0);
		this.ec.editEmployee("444.555.666-00", "", "", "", "", "", "", 4000);

		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario@funcionario.com",
				new Address("Rua das Rosas", "Jardim", "Estufa", "Floricultura"), "stock", 4000);

		Assert.assertEquals(e.toString(), this.ec.searchEmployee("444.555.666-00").toString());
	}

	// Test "single EDIT in EmployeeDatabase with multiple employees"
	@Test
	public void testEdit04() throws DatabaseErrorException {
		this.ec.addEmployee("Joana D'Arc", "999.888.777-66", "jdc@jdc.com", "Avenida", "Cidade", "Estado", "Pais",
				"stock", 1300);

		this.ec.editEmployee("444.555.666-00", "funcionario@funcionario.com", "Rua das Rosas", "Jardim", "Estufa",
				"Floricultura", "stock", 4000);
		Employee e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario@funcionario.com",
				new Address("Rua das Rosas", "Jardim", "Estufa", "Floricultura"), "stock", 4000);

		Assert.assertEquals(e.toString(), this.ec.searchEmployee("444.555.666-00").toString());
	}

	// Test "EDIT an employee inexistent in EmployeeDatabase"
	@Test
	public void testEdit05() throws DatabaseErrorException {
		try {
			this.ec.editEmployee("111.222.333-00", "", "", "", "", "Floricultura", "", 0);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - EmployeeControl EDIT: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}
}