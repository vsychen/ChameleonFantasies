package test.business.control.accessControl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.control.AccessControl;
import main.business.exceptions.LoginErrorException;
import main.data.database.CustomerDatabase;
import main.data.database.EmployeeDatabase;
import main.data.database.FantasyDatabase;
import main.data.entities.Address;
import main.data.entities.Cloth;
import main.data.entities.Customer;
import main.data.entities.Employee;
import main.data.entities.Fantasy;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the REPORT method in AccessControl.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class AccessControlReportTest {
	private AccessControl ac;
	private CustomerDatabase cd;
	private EmployeeDatabase ed;
	private FantasyDatabase fd;

	private Customer c;
	private Employee e;
	private Fantasy f;

	@Before
	public void setUp() throws DatabaseErrorException {
		this.cd = new CustomerDatabase();
		this.ed = new EmployeeDatabase();
		this.fd = new FantasyDatabase();
		this.ac = new AccessControl(this.cd, this.ed, this.fd);

		this.c = new Customer("José Araújo", "111.222.333-00");
		this.e = new Employee("Marcos da Silva", "444.555.666-00", "funcionario@ufncoinairo.com",
				new Address("Avenida", "Cidade", "Estado", "Pais"), "admin", 3000);
		this.f = new Fantasy("Hulk", "1", null, null, new Cloth("Calça", "Roxa", false, false), null, null, 20, 100,
				300);

		this.cd.add(this.c);
		this.ed.add(this.e);
		this.fd.add(this.f);
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		(new CustomerDatabase()).removeAll();
		(new EmployeeDatabase()).removeAll();
		(new FantasyDatabase()).removeAll();

		this.ac = null;
	}

	// Test "REPORT from CustomerDatabase"
	@Test
	public void testReport01() throws DatabaseErrorException {
		Assert.assertEquals(this.c.toString() + "\n", this.ac.getReport(0));
	}

	// Test "REPORT from EmployeeDatabase"
	@Test
	public void testReport02() throws DatabaseErrorException {
		Assert.assertEquals(this.e.toString() + "\n", this.ac.getReport(1));
	}

	// Test "REPORT from FantasyDatabase"
	@Test
	public void testReport03() throws DatabaseErrorException {
		Assert.assertEquals(this.f.toString() + "\n", this.ac.getReport(2));
	}

	// Test "REPORT from CustomerDatabase (2)"
	@Test
	public void testReport04() throws DatabaseErrorException {
		Customer c = new Customer("José Araújo", "111.111.111-11");
		this.cd.add(c);

		Assert.assertEquals(this.c.toString() + "\n" + c.toString() + "\n", this.ac.getReport(0));
	}

	// Test "REPORT from EmployeeDatabase (2)"
	@Test
	public void testReport05() throws DatabaseErrorException {
		Employee e = new Employee("Amanda Morais", "444.444.444-44", "funcionario2@ufncoinairo.com",
				new Address("Avenida", "Cidade", "Estado", "Pais"), "cashier", 3000);
		this.ed.add(e);

		Assert.assertEquals(e.toString() + "\n" + this.e.toString() + "\n", this.ac.getReport(1));
	}

	// Test "REPORT from FantasyDatabase (2)"
	@Test
	public void testReport06() throws DatabaseErrorException {
		Fantasy f = new Fantasy("Batman", "2", new Cloth("Máscara", "Preta", false, false),
				new Cloth("Suéter", "Cinza", false, true), new Cloth("Calça", "Roxa", false, false),
				new Cloth("Luva", "Preta", false, false), new Cloth("Bota", "Preta", false, false), 10, 400, 800);
		this.fd.add(f);

		Assert.assertEquals(this.f.toString() + "\n" + f.toString() + "\n", this.ac.getReport(2));
	}
}