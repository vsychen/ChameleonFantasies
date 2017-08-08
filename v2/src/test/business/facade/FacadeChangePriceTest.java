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
import main.data.database.CustomerDatabase;
import main.data.database.EmployeeDatabase;
import main.data.database.FantasyDatabase;
import main.data.entities.Fantasy;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the CHANGE PRICE method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeChangePriceTest {
	private Facade facade;
	private String adminPassword;
	private String cashierPassword;

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

		Object[] aHat = { "Máscara", "Preta", false, false };
		Object[] aTop = { "Suéter", "Preto", true, false };
		Object[] aBottom = { "Calça", "Preta", false, false };
		Object[] aArms = { "Luva", "Preta", false, false };
		Object[] aShoes = { "Sandália", "Preta", false, false };

		this.facade.addFantasy("Batman", "1", aHat, aTop, aBottom, aArms, aShoes, 10, 500, 1000);
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		new CustomerDatabase().removeAll();
		new EmployeeDatabase().removeAll();
		new FantasyDatabase().removeAll();

		this.facade.close();
	}

	// Test "CHANGE BUY AND SELL PRICES to a greater amount"
	@Test
	public void testChangePrice01()
			throws LoginErrorException, UnauthorizedActionException, InvalidFieldException, DatabaseErrorException {
		this.facade.changePrices("1", 600, 1200);
		Fantasy f = this.facade.seeFantasy("1");

		Assert.assertEquals(true, f.getBuyPrice() == 600 && f.getSellPrice() == 1200);
	}

	// Test "CHANGE BUY AND SELL PRICES to a lower amount"
	@Test
	public void testChangePrice02()
			throws LoginErrorException, UnauthorizedActionException, InvalidFieldException, DatabaseErrorException {
		this.facade.changePrices("1", 400, 800);
		Fantasy f = this.facade.seeFantasy("1");

		Assert.assertEquals(true, f.getBuyPrice() == 400 && f.getSellPrice() == 800);
	}

	// Test "CHANGE BUY AND SELL PRICES to a equal amount"
	@Test
	public void testChangePrice03()
			throws LoginErrorException, UnauthorizedActionException, InvalidFieldException, DatabaseErrorException {
		this.facade.changePrices("1", 500, 1000);
		Fantasy f = this.facade.seeFantasy("1");

		Assert.assertEquals(true, f.getBuyPrice() == 500 && f.getSellPrice() == 1000);
	}

	// Test "CHANGE BUY AND SELL PRICES when not logged in"
	@Test
	public void testChangePrice04()
			throws LoginErrorException, UnauthorizedActionException, InvalidFieldException, DatabaseErrorException {
		this.facade.logout();

		try {
			this.facade.changePrices("1", 600, 1200);
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade CHANGE PRICES: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}

	// Test "CHANGE BUY AND SELL PRICES when not an admin"
	@Test
	public void testChangePrice05() throws LoginErrorException, InvalidFieldException, DatabaseErrorException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.cashierPassword);

		try {
			this.facade.changePrices("1", 600, 1200);
		} catch (UnauthorizedActionException e) {
			System.out.println("UnauthorizedActionException - Facade CHANGE PRICES: " + e.getMessage());
			Assert.assertEquals("A conta logada não possui permissão para realizar esta ação.", e.getMessage());
		}
	}

	// Test "CHANGE SELL PRICE to a greater amount"
	@Test
	public void testChangePrice06()
			throws LoginErrorException, UnauthorizedActionException, InvalidFieldException, DatabaseErrorException {
		this.facade.changePrices("1", 0, 1200);
		Fantasy f = this.facade.seeFantasy("1");

		Assert.assertEquals(true, f.getBuyPrice() == 500 && f.getSellPrice() == 1200);
	}

	// Test "CHANGE BUY PRICE to a greater amount"
	@Test
	public void testChangePrice07()
			throws LoginErrorException, UnauthorizedActionException, InvalidFieldException, DatabaseErrorException {
		this.facade.changePrices("1", 600, 0);
		Fantasy f = this.facade.seeFantasy("1");

		Assert.assertEquals(true, f.getBuyPrice() == 600 && f.getSellPrice() == 1000);
	}

	// Test "CHANGE PRICES to invalid values"
	@Test
	public void testChangePrice08()
			throws LoginErrorException, UnauthorizedActionException, InvalidFieldException, DatabaseErrorException {
		try {
			this.facade.changePrices("1", 1200, 600);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade CHANGE PRICES: " + e.getMessage());
			Assert.assertEquals("Os campos PREÇODECOMPRA, PREÇODEVENDA não foram inseridos de forma correta.",
					e.getMessage());
		}
	}

	// Test "CHANGE PRICES to invalid values"
	@Test
	public void testChangePrice09()
			throws LoginErrorException, UnauthorizedActionException, InvalidFieldException, DatabaseErrorException {
		try {
			this.facade.changePrices("1", 1200, 1200);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade CHANGE PRICES: " + e.getMessage());
			Assert.assertEquals("Os campos PREÇODECOMPRA, PREÇODEVENDA não foram inseridos de forma correta.",
					e.getMessage());
		}
	}
}