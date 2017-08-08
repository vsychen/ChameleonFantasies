package test.business.facade;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.exceptions.InsufficientStockException;
import main.business.exceptions.InvalidFieldException;
import main.business.exceptions.LoginErrorException;
import main.business.exceptions.NotFirstAccountException;
import main.business.exceptions.UnauthorizedActionException;
import main.business.facade.Facade;
import main.data.database.CustomerDatabase;
import main.data.database.EmployeeDatabase;
import main.data.database.FantasyDatabase;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the BUY and SELL methods in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeBuySellFantasyTest {
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

	// Test "BUY FANTASY with a stock account"
	@Test
	public void testBuyFantasy01()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();
		this.facade.login("333.333.333-33", this.stockPassword);
		this.facade.buyFantasy("1", 10);

		Assert.assertEquals(20, this.facade.seeFantasy("1").getQuantity());
	}

	// Test "BUY FANTASY when not logged in"
	@Test
	public void testBuyFantasy02()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();

		try {
			this.facade.buyFantasy("1", 10);
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade BUY FANTASY: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}

	// Test "BUY FANTASY when not a stock employee"
	@Test
	public void testBuyFantasy03()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.cashierPassword);

		try {
			this.facade.buyFantasy("1", 10);
		} catch (UnauthorizedActionException e) {
			System.out.println("UnauthorizedActionException - Facade BUY FANTASY: " + e.getMessage());
			Assert.assertEquals("A conta logada não possui permissão para realizar esta ação.", e.getMessage());
		}
	}

	// Test "BUY an invalid quantity of fantasies"
	@Test
	public void testBuyFantasy04()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();
		this.facade.login("333.333.333-33", this.stockPassword);

		try {
			this.facade.buyFantasy("1", 0);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade BUY FANTASY: " + e.getMessage());
			Assert.assertEquals("O campo QUANTIDADE não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "BUY a negative quantity of fantasies"
	@Test
	public void testBuyFantasy05()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();
		this.facade.login("333.333.333-33", this.stockPassword);

		try {
			this.facade.buyFantasy("1", -10);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade BUY FANTASY: " + e.getMessage());
			Assert.assertEquals("O campo QUANTIDADE não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "SELL FANTASY checking quantity of fantasies"
	@Test
	public void testSellFantasy01() throws LoginErrorException, InvalidFieldException, UnauthorizedActionException,
			DatabaseErrorException, InsufficientStockException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.cashierPassword);

		this.facade.addCustomer("Lucas dos Anjos", "999.999.999-99");
		this.facade.sellFantasy("999.999.999-99", "1", 10);

		Assert.assertEquals(0, this.facade.seeFantasy("1").getQuantity());
	}

	// Test "SELL FANTASY checking customer spending value"
	@Test
	public void testSellFantasy02() throws LoginErrorException, InvalidFieldException, UnauthorizedActionException,
			DatabaseErrorException, InsufficientStockException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.cashierPassword);

		this.facade.addCustomer("Lucas dos Anjos", "999.999.999-99");
		this.facade.sellFantasy("999.999.999-99", "1", 5);

		Assert.assertEquals(5000, this.facade.seeCustomer("999.999.999-99").getSpending(), 0.01);
	}

	// Test "SELL FANTASY when not logged in"
	@Test
	public void testSellFantasy03() throws LoginErrorException, InvalidFieldException, UnauthorizedActionException,
			DatabaseErrorException, InsufficientStockException {
		this.facade.logout();

		try {
			this.facade.sellFantasy("999.999.999-99", "1", 10);
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade SELL FANTASY: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}

	// Test "SELL FANTASY when not a cashier"
	@Test
	public void testSellFantasy04() throws LoginErrorException, InvalidFieldException, UnauthorizedActionException,
			DatabaseErrorException, InsufficientStockException {
		this.facade.logout();
		this.facade.login("333.333.333-33", this.stockPassword);

		try {
			this.facade.sellFantasy("999.999.999-99", "1", 10);
		} catch (UnauthorizedActionException e) {
			System.out.println("UnauthorizedActionException - Facade SELL FANTASY: " + e.getMessage());
			Assert.assertEquals("A conta logada não possui permissão para realizar esta ação.", e.getMessage());
		}
	}

	// Test "SELL FANTASY with invalid customer cpf"
	@Test
	public void testSellFantasy05() throws LoginErrorException, InvalidFieldException, UnauthorizedActionException,
			DatabaseErrorException, InsufficientStockException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.cashierPassword);

		this.facade.addCustomer("Lucas dos Anjos", "999.999.999-99");

		try {
			this.facade.sellFantasy("9", "1", 10);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade SELL FANTASY: " + e.getMessage());
			Assert.assertEquals("O campo CPF não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "SELL invalid quantity of fantasies"
	@Test
	public void testSellFantasy06() throws LoginErrorException, InvalidFieldException, UnauthorizedActionException,
			DatabaseErrorException, InsufficientStockException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.cashierPassword);

		this.facade.addCustomer("Lucas dos Anjos", "999.999.999-99");

		try {
			this.facade.sellFantasy("999.999.999-99", "1", 0);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade SELL FANTASY: " + e.getMessage());
			Assert.assertEquals("O campo QUANTIDADE não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "SELL negative quantity of fantasies"
	@Test
	public void testSellFantasy07() throws LoginErrorException, InvalidFieldException, UnauthorizedActionException,
			DatabaseErrorException, InsufficientStockException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.cashierPassword);

		this.facade.addCustomer("Lucas dos Anjos", "999.999.999-99");

		try {
			this.facade.sellFantasy("999.999.999-99", "1", -10);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade SELL FANTASY: " + e.getMessage());
			Assert.assertEquals("O campo QUANTIDADE não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "SELL quantity of fantasies greater than what have in stock"
	@Test
	public void testSellFantasy08() throws LoginErrorException, InvalidFieldException, UnauthorizedActionException,
			DatabaseErrorException, InsufficientStockException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.cashierPassword);

		this.facade.addCustomer("Lucas dos Anjos", "999.999.999-99");

		try {
			this.facade.sellFantasy("999.999.999-99", "1", 20);
		} catch (InsufficientStockException e) {
			System.out.println("InsufficientStockException - Facade SELL FANTASY: " + e.getMessage());
			Assert.assertEquals("Não há estoque suficiente. Quantidade desejada: 20. Quantidade em estoque: 10.",
					e.getMessage());
		}
	}
}