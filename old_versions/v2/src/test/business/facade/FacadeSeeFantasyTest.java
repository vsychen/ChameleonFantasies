package test.business.facade;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.NotFirstAccountException;
import main.business.exceptions.UnauthorizedActionException;
import main.business.facade.Facade;
import main.data.database.EmployeeDatabase;
import main.data.database.FantasyDatabase;
import main.data.entities.Cloth;
import main.data.entities.Fantasy;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the SEE FANTASY method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeSeeFantasyTest {
	private Facade facade;
	private String adminPassword;
	private String cashierPassword;
	private String stockPassword;

	@Before
	public void setUp() throws DatabaseErrorException, NotFirstAccountException, InvalidFieldException,
			LoginErrorException, UnauthorizedActionException {
		this.facade = Facade.getInstance();
		this.adminPassword = this.facade.firstAccess("Marcos da Silva", "111.111.111-11", "funcionario@ufncoinairo.com",
				"Avenida", "Cidade", "Estado", "Pais", 3000);

		this.facade.login("111.111.111-11", adminPassword);
		this.facade.addEmployee("Luana Medeiros", "222.222.222-22", "func1@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "cashier", 1500);
		this.cashierPassword = this.facade.seeEmployee("222.222.222-22").getPassword();

		this.facade.addEmployee("Marcos da Silva", "333.333.333-33", "func2@ufncoinairo.com", "Avenida", "Cidade",
				"Estado", "Pais", "stock", 1500);
		this.stockPassword = this.facade.seeEmployee("333.333.333-33").getPassword();

		Object[] aBottom = { "Bermuda", "Roxa", false, false };

		this.facade.addFantasy("Hulk", "1", null, null, aBottom, null, null, 50, 200, 400);
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		new EmployeeDatabase().removeAll();
		new FantasyDatabase().removeAll();

		this.facade.close();
	}

	// Test "simple SEE FANTASY using admin account"
	@Test
	public void testSeeFantasy01() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		Cloth cBottom = new Cloth("Bermuda", "Roxa", false, false);
		Fantasy f = new Fantasy("Hulk", "1", null, null, cBottom, null, null, 50, 200, 400);

		Assert.assertEquals(f.toString(), this.facade.seeFantasy("1").toString());
	}

	// Test "simple SEE FANTASY using cashier account"
	@Test
	public void testSeeFantasy02()
			throws LoginErrorException, InvalidFieldException, UnauthorizedActionException, DatabaseErrorException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.cashierPassword);

		Cloth cBottom = new Cloth("Bermuda", "Roxa", false, false);
		Fantasy f = new Fantasy("Hulk", "1", null, null, cBottom, null, null, 50, 200, 400);

		Assert.assertEquals(f.toString(), this.facade.seeFantasy("1").toString());
	}

	// Test "simple SEE FANTASY using stock employee account"
	@Test
	public void testSeeFantasy03()
			throws LoginErrorException, InvalidFieldException, UnauthorizedActionException, DatabaseErrorException {
		this.facade.logout();
		this.facade.login("333.333.333-33", this.stockPassword);

		Cloth cBottom = new Cloth("Bermuda", "Roxa", false, false);
		Fantasy f = new Fantasy("Hulk", "1", null, null, cBottom, null, null, 50, 200, 400);

		Assert.assertEquals(f.toString(), this.facade.seeFantasy("1").toString());
	}

	// Test "simple SEE FANTASY in database with multiple fantasies"
	@Test
	public void testSeeFantasy04()
			throws LoginErrorException, InvalidFieldException, UnauthorizedActionException, DatabaseErrorException {
		Object[] bHat = { "Máscara", "Preta", false, false };
		Object[] bTop = { "Suéter", "Cinza", true, false };
		Object[] bBottom = { "Calça", "Cinza", false, false };
		Object[] bArms = { "Luva", "Preta", false, false };
		Object[] bShoes = { "Sapato", "Preto", false, false };

		this.facade.addFantasy("Batman", "2", bHat, bTop, bBottom, bArms, bShoes, 10, 500, 1000);

		Cloth cBottom = new Cloth("Bermuda", "Roxa", false, false);
		Fantasy f = new Fantasy("Hulk", "1", null, null, cBottom, null, null, 50, 200, 400);

		Assert.assertEquals(f.toString(), this.facade.seeFantasy("1").toString());
	}

	// Test "SEE FANTASY when not logged in"
	@Test
	public void testSeeFantasy05() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		this.facade.logout();

		try {
			this.facade.seeFantasy("1").toString();
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade SEE FANTASY: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}
}