package test.business.control.fantasyControl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.control.FantasyControl;
import main.data.database.CustomerDatabase;
import main.data.database.FantasyDatabase;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the CHANGE PRICE method in FantasyControl.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FantasyControlChangePriceTest {
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

	// Test "CHANGE PRICES to 0/0"
	@Test
	public void testChangePrice01() throws DatabaseErrorException {
		this.fc.changePrice("1", 0, 0);

		Assert.assertEquals(true,
				this.fc.searchFantasy("1").getBuyPrice() == 500 && this.fc.searchFantasy("1").getSellPrice() == 1000);
	}

	// Test "CHANGE PRICES to neg/neg"
	@Test
	public void testChangePrice02() throws DatabaseErrorException {
		this.fc.changePrice("1", -1, -1);

		Assert.assertEquals(true,
				this.fc.searchFantasy("1").getBuyPrice() == 500 && this.fc.searchFantasy("1").getSellPrice() == 1000);
	}

	// Test "CHANGE PRICES to 100/200"
	@Test
	public void testChangePrice03() throws DatabaseErrorException {
		this.fc.changePrice("1", 100, 200);

		Assert.assertEquals(true,
				this.fc.searchFantasy("1").getBuyPrice() == 100 && this.fc.searchFantasy("1").getSellPrice() == 200);
	}

	// Test "CHANGE PRICES to 200/200"
	@Test
	public void testChangePrice04() throws DatabaseErrorException {
		this.fc.changePrice("1", 200, 200);

		Assert.assertEquals(true,
				this.fc.searchFantasy("1").getBuyPrice() == 500 && this.fc.searchFantasy("1").getSellPrice() == 1000);
	}

	// Test "CHANGE PRICES to 300/200"
	@Test
	public void testChangePrice05() throws DatabaseErrorException {
		this.fc.changePrice("1", 300, 200);

		Assert.assertEquals(true,
				this.fc.searchFantasy("1").getBuyPrice() == 500 && this.fc.searchFantasy("1").getSellPrice() == 1000);
	}

	// Test "CHANGE PRICES to 500/1000"
	@Test
	public void testChangePrice06() throws DatabaseErrorException {
		this.fc.changePrice("1", 500, 1000);

		Assert.assertEquals(true,
				this.fc.searchFantasy("1").getBuyPrice() == 500 && this.fc.searchFantasy("1").getSellPrice() == 1000);
	}

	// Test "CHANGE PRICES to 600/1200"
	@Test
	public void testChangePrice07() throws DatabaseErrorException {
		this.fc.changePrice("1", 600, 1200);

		Assert.assertEquals(true,
				this.fc.searchFantasy("1").getBuyPrice() == 600 && this.fc.searchFantasy("1").getSellPrice() == 1200);
	}

	// Test "CHANGE PRICES to 1200/1200"
	@Test
	public void testChangePrice08() throws DatabaseErrorException {
		this.fc.changePrice("1", 1200, 1200);

		Assert.assertEquals(true,
				this.fc.searchFantasy("1").getBuyPrice() == 500 && this.fc.searchFantasy("1").getSellPrice() == 1000);
	}

	// Test "CHANGE PRICES to 1200/600"
	@Test
	public void testChangePrice09() throws DatabaseErrorException {
		this.fc.changePrice("1", 1200, 600);

		Assert.assertEquals(true,
				this.fc.searchFantasy("1").getBuyPrice() == 500 && this.fc.searchFantasy("1").getSellPrice() == 1000);
	}
}