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
 * Tests for the ADD FANTASY method in Facade.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FacadeAddFantasyTest {
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
	}

	@After
	public void tearDown() throws DatabaseErrorException, LoginErrorException {
		new EmployeeDatabase().removeAll();
		new FantasyDatabase().removeAll();

		this.facade.close();
	}

	// Test "simple ADD FANTASY with an admin account"
	@Test
	public void testAddFantasy01()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		Object[] aHat = { "Máscara", "Preta", false, false };
		Object[] aTop = { "Suéter", "Preto", true, false };
		Object[] aBottom = { "Calça", "Preta", false, false };
		Object[] aArms = { "Luva", "Preta", false, false };
		Object[] aShoes = { "Sandália", "Preta", false, false };

		Cloth cHat = new Cloth("Máscara", "Preta", false, false);
		Cloth cTop = new Cloth("Suéter", "Preto", true, false);
		Cloth cBottom = new Cloth("Calça", "Preta", false, false);
		Cloth cArms = new Cloth("Luva", "Preta", false, false);
		Cloth cShoes = new Cloth("Sandália", "Preta", false, false);

		this.facade.addFantasy("Batman", "1", aHat, aTop, aBottom, aArms, aShoes, 10, 500, 1000);
		Fantasy f = new Fantasy("Batman", "1", cHat, cTop, cBottom, cArms, cShoes, 10, 500, 1000);
		Assert.assertEquals(f.toString(), this.facade.seeFantasy("1").toString());
	}

	// Test "simple ADD FANTASY with a stock account"
	@Test
	public void testAddFantasy02()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();
		this.facade.login("333.333.333-33", this.stockPassword);

		Object[] aHat = { "Máscara", "Preta", false, false };
		Object[] aTop = { "Suéter", "Preto", true, false };
		Object[] aBottom = { "Calça", "Preta", false, false };
		Object[] aArms = { "Luva", "Preta", false, false };
		Object[] aShoes = { "Sandália", "Preta", false, false };

		Cloth cHat = new Cloth("Máscara", "Preta", false, false);
		Cloth cTop = new Cloth("Suéter", "Preto", true, false);
		Cloth cBottom = new Cloth("Calça", "Preta", false, false);
		Cloth cArms = new Cloth("Luva", "Preta", false, false);
		Cloth cShoes = new Cloth("Sandália", "Preta", false, false);

		this.facade.addFantasy("Batman", "1", aHat, aTop, aBottom, aArms, aShoes, 10, 500, 1000);
		Fantasy f = new Fantasy("Batman", "1", cHat, cTop, cBottom, cArms, cShoes, 10, 500, 1000);
		Assert.assertEquals(f.toString(), this.facade.seeFantasy("1").toString());
	}

	// Test "consecutive ADD FANTASY"
	@Test
	public void testAddFantasy03()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		Object[] aHat = { "Máscara", "Preta", false, false };
		Object[] aTop = { "Suéter", "Preto", true, false };
		Object[] aBottom = { "Calça", "Preta", false, false };
		Object[] aArms = { "Luva", "Preta", false, false };
		Object[] aShoes = { "Sandália", "Preta", false, false };

		Cloth cHat = new Cloth("Máscara", "Preta", false, false);
		Cloth cTop = new Cloth("Suéter", "Preto", true, false);
		Cloth cBottom = new Cloth("Calça", "Preta", false, false);
		Cloth cArms = new Cloth("Luva", "Preta", false, false);
		Cloth cShoes = new Cloth("Sandália", "Preta", false, false);

		Object[] aux_cBottom = { "Bermuda", "Roxa", false, false };

		this.facade.addFantasy("Hulk", "1", null, null, aux_cBottom, null, null, 50, 200, 400);
		this.facade.addFantasy("Batman", "2", aHat, aTop, aBottom, aArms, aShoes, 10, 500, 1000);
		Fantasy f = new Fantasy("Batman", "2", cHat, cTop, cBottom, cArms, cShoes, 10, 500, 1000);
		Assert.assertEquals(f.toString(), this.facade.seeFantasy("2").toString());
	}

	// Test "ADD FANTASY when not logged in"
	@Test
	public void testAddFantasy04()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();

		Object[] aHat = { "Máscara", "Preta", false, false };
		Object[] aTop = { "Suéter", "Preto", true, false };
		Object[] aBottom = { "Calça", "Preta", false, false };
		Object[] aArms = { "Luva", "Preta", false, false };
		Object[] aShoes = { "Sandália", "Preta", false, false };

		try {
			this.facade.addFantasy("Batman", "1", aHat, aTop, aBottom, aArms, aShoes, 10, 500, 1000);
		} catch (LoginErrorException e) {
			System.out.println("LoginErrorException - Facade ADD FANTASY: " + e.getMessage());
			Assert.assertEquals("Não há conta logada no sistema.", e.getMessage());
		}
	}

	// Test "ADD FANTASY when not an admin or a stock employee"
	@Test
	public void testAddFantasy05()
			throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException, InvalidFieldException {
		this.facade.logout();
		this.facade.login("222.222.222-22", this.cashierPassword);

		Object[] aHat = { "Máscara", "Preta", false, false };
		Object[] aTop = { "Suéter", "Preto", true, false };
		Object[] aBottom = { "Calça", "Preta", false, false };
		Object[] aArms = { "Luva", "Preta", false, false };
		Object[] aShoes = { "Sandália", "Preta", false, false };

		try {
			this.facade.addFantasy("Batman", "1", aHat, aTop, aBottom, aArms, aShoes, 10, 500, 1000);
		} catch (UnauthorizedActionException e) {
			System.out.println("UnauthorizedActionException - Facade ADD FANTASY: " + e.getMessage());
			Assert.assertEquals("A conta logada não possui permissão para realizar esta ação.", e.getMessage());
		}
	}

	// Test "ADD FANTASY with invalid name"
	@Test
	public void testAddFantasy06() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		Object[] aBottom = { "Calça", "Roxa", false, false };

		try {
			this.facade.addFantasy("H", "1", null, null, aBottom, null, null, 50, 200, 400);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD FANTASY: " + e.getMessage());
			Assert.assertEquals("O campo NOME não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD FANTASY with negative quantity"
	@Test
	public void testAddFantasy07() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		Object[] aBottom = { "Calça", "Roxa", false, false };

		try {
			this.facade.addFantasy("Hulk", "1", null, null, aBottom, null, null, -1, 200, 400);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD FANTASY: " + e.getMessage());
			Assert.assertEquals("O campo QUANTIDADE não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD FANTASY with invalid buy price"
	@Test
	public void testAddFantasy08() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		Object[] aBottom = { "Calça", "Roxa", false, false };

		try {
			this.facade.addFantasy("Hulk", "1", null, null, aBottom, null, null, 10, 0, 400);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD FANTASY: " + e.getMessage());
			Assert.assertEquals("O campo PREÇODECOMPRA não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD FANTASY with invalid sell price"
	@Test
	public void testAddFantasy09() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		Object[] aBottom = { "Calça", "Roxa", false, false };

		try {
			this.facade.addFantasy("Hulk", "1", null, null, aBottom, null, null, 10, 10, 0);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD FANTASY: " + e.getMessage());
			Assert.assertEquals("O campo PREÇODEVENDA não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD FANTASY with invalid buy/sell price"
	@Test
	public void testAddFantasy10() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		Object[] aBottom = { "Calça", "Roxa", false, false };

		try {
			this.facade.addFantasy("Hulk", "1", null, null, aBottom, null, null, 10, 100, 50);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD FANTASY: " + e.getMessage());
			Assert.assertEquals("O campo PREÇODEVENDA não foi inserido de forma correta.", e.getMessage());
		}
	}

	// Test "ADD FANTASY with multiple invalid fields"
	@Test
	public void testAddFantasy11() throws LoginErrorException, UnauthorizedActionException, DatabaseErrorException {
		Object[] aBottom = { "Calça", "Roxa", false, false };

		try {
			this.facade.addFantasy("H", "1", null, null, aBottom, null, null, -1, -1, -1);
		} catch (InvalidFieldException e) {
			System.out.println("InvalidFieldException - Facade ADD FANTASY: " + e.getMessage());
			Assert.assertEquals(
					"Os campos NOME, QUANTIDADE, PREÇODECOMPRA, PREÇODEVENDA não foram inseridos de forma correta.",
					e.getMessage());
		}
	}
}