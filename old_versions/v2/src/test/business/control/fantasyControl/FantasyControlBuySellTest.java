package test.business.control.fantasyControl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.control.FantasyControl;
import main.business.exceptions.InsufficientStockException;
import main.data.database.CustomerDatabase;
import main.data.database.FantasyDatabase;
import main.data.entities.Customer;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the BUY and SELL methods in FantasyControl.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FantasyControlBuySellTest {
	private FantasyControl fc;

	@Before
	public void setUp() throws DatabaseErrorException {
		this.fc = new FantasyControl(new FantasyDatabase());

		Object[] hat = { "Máscara", "Preta", false, false };
		Object[] top = { "Suéter", "Cinza", false, true };
		Object[] bottom = { "Calça", "Cinza", false, false };
		Object[] arms = { "Luva", "Preta", false, false };
		Object[] shoes = { "Bota", "Preta", false, false };
		this.fc.addFantasy("Batman", "1", hat, top, bottom, arms, shoes, 10, 500, 1000);
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		(new FantasyDatabase()).removeAll();
		(new CustomerDatabase()).removeAll();
		this.fc = null;
	}

	// Test "BUY a positive quantity of fantasies"
	@Test
	public void testBuy01() throws DatabaseErrorException {
		this.fc.buyFantasy("1", 10);

		Assert.assertEquals(20, this.fc.searchFantasy("1").getQuantity());
	}

	// Test "BUY a neutral quantity of fantasies"
	@Test
	public void testBuy02() throws DatabaseErrorException {
		this.fc.buyFantasy("1", 0);

		Assert.assertEquals(10, this.fc.searchFantasy("1").getQuantity());
	}

	// Test "BUY a negative quantity of fantasies"
	@Test
	public void testBuy03() throws DatabaseErrorException {
		this.fc.buyFantasy("1", -10);

		Assert.assertEquals(10, this.fc.searchFantasy("1").getQuantity());
	}

	// Test "BUY a fantasy inexistent in database"
	@Test
	public void testBuy04() {
		try {
			this.fc.buyFantasy("2", 10);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - FantasyControl BUY: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}

	// Test "SELL a positive quantity of fantasies"
	@Test
	public void testSell01() throws DatabaseErrorException, InsufficientStockException {
		CustomerDatabase cd = new CustomerDatabase();
		cd.add(new Customer("Leonardo Vincente", "111.222.333-00"));
		this.fc.sellFantasy(cd, "111.222.333-00", "1", 10);

		double c_spending = cd.search("111.222.333-00").getSpending();
		int f_quantity = (new FantasyDatabase()).search("1").getQuantity();

		Assert.assertEquals(true, c_spending > 0 && f_quantity == 0);
	}

	// Test "SELL a neutral quantity of fantasies"
	@Test
	public void testSell02() throws DatabaseErrorException, InsufficientStockException {
		CustomerDatabase cd = new CustomerDatabase();
		cd.add(new Customer("Leonardo Vincente", "111.222.333-00"));
		this.fc.sellFantasy(cd, "111.222.333-00", "1", 0);

		double c_spending = cd.search("111.222.333-00").getSpending();
		int f_quantity = (new FantasyDatabase()).search("1").getQuantity();

		Assert.assertEquals(true, c_spending == 0 && f_quantity == 10);
	}

	// Test "SELL a negative quantity of fantasies"
	@Test
	public void testSell03() throws DatabaseErrorException, InsufficientStockException {
		CustomerDatabase cd = new CustomerDatabase();
		cd.add(new Customer("Leonardo Vincente", "111.222.333-00"));
		this.fc.sellFantasy(cd, "111.222.333-00", "1", -10);

		double c_spending = cd.search("111.222.333-00").getSpending();
		int f_quantity = (new FantasyDatabase()).search("1").getQuantity();

		Assert.assertEquals(true, c_spending == 0 && f_quantity == 10);
	}

	// Test "SELL a fantasy inexistent in database"
	@Test
	public void testSell04() throws InsufficientStockException {
		try {
			CustomerDatabase cd = new CustomerDatabase();
			cd.add(new Customer("Leonardo Vincente", "111.222.333-00"));
			this.fc.sellFantasy(cd, "111.222.333-00", "2", 10);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - FantasyControl SELL: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}

	// Test "SELL a fantasy to a customer inexistent in database"
	@Test
	public void testSell05() throws InsufficientStockException {
		try {
			this.fc.sellFantasy(new CustomerDatabase(), "111.222.333-00", "2", 10);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - FantasyControl SELL: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}
}