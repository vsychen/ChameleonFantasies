package test.business.control.fantasyControl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.business.control.FantasyControl;
import main.data.database.FantasyDatabase;
import main.data.entities.Cloth;
import main.data.entities.Fantasy;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the ADD and SEARCH methods in FantasyControl.
 *
 * @author Victor Chen (vsyc)
 *
 */
public class FantasyControlAddSearchTest {
	private FantasyControl fc;
	private Cloth hat, top, bottom, arms, shoes;

	@Before
	public void setUp() {
		this.fc = new FantasyControl(new FantasyDatabase());

		this.hat = new Cloth("Máscara", "Preta", false, false);
		this.top = new Cloth("Suéter", "Cinza", false, true);
		this.bottom = new Cloth("Calça", "Cinza", false, false);
		this.arms = new Cloth("Luva", "Preta", false, false);
		this.shoes = new Cloth("Bota", "Preta", false, false);
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		(new FantasyDatabase()).removeAll();
		this.fc = null;
	}

	// Test "simple ADD and SEARCH in FantasyDatabase"
	@Test
	public void testAddSearch01() throws DatabaseErrorException {
		Object[] hat = { "Máscara", "Preta", false, false };
		Object[] top = { "Suéter", "Cinza", false, true };
		Object[] bottom = { "Calça", "Cinza", false, false };
		Object[] arms = { "Luva", "Preta", false, false };
		Object[] shoes = { "Bota", "Preta", false, false };

		this.fc.addFantasy("Batman", "1", hat, top, bottom, arms, shoes, 10, 500, 1000);
		Fantasy f = new Fantasy("Batman", "1", this.hat, this.top, this.bottom, this.arms, this.shoes, 10, 500, 1000);

		Assert.assertEquals(f.toString(), this.fc.searchFantasy("1").toString());
	}

	// Test "ADD and SEARCH in FantasyDatabase with multiple fantasies"
	@Test
	public void testAddSearch02() throws DatabaseErrorException {
		Object[] aux_bottom = { "Calça", "Roxa", false, false };

		this.fc.addFantasy("Hulk", "2", null, null, aux_bottom, null, null, 50, 100, 300);

		Object[] hat = { "Máscara", "Preta", false, false };
		Object[] top = { "Suéter", "Cinza", false, true };
		Object[] bottom = { "Calça", "Cinza", false, false };
		Object[] arms = { "Luva", "Preta", false, false };
		Object[] shoes = { "Bota", "Preta", false, false };

		this.fc.addFantasy("Batman", "1", hat, top, bottom, arms, shoes, 10, 500, 1000);
		Fantasy e = new Fantasy("Batman", "1", this.hat, this.top, this.bottom, this.arms, this.shoes, 10, 500, 1000);

		Assert.assertEquals(e.toString(), this.fc.searchFantasy("1").toString());
	}

	// Test "ADD duplicated fantasy in FantasyDatabase"
	@Test
	public void testAdd01() throws DatabaseErrorException {
		Object[] hat = { "Máscara", "Preta", false, false };
		Object[] top = { "Suéter", "Cinza", false, true };
		Object[] bottom = { "Calça", "Cinza", false, false };
		Object[] arms = { "Luva", "Preta", false, false };
		Object[] shoes = { "Bota", "Preta", false, false };

		this.fc.addFantasy("Batman", "1", hat, top, bottom, arms, shoes, 10, 500, 1000);

		try {
			this.fc.addFantasy("Batman", "1", hat, top, bottom, arms, shoes, 10, 500, 1000);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - FantasyControl ADD: " + e.getMessage());
			Assert.assertEquals("Item já existe na base de dados.", e.getMessage());
		}
	}

	// Test "SEARCH for fantasy inexistent in FantasyDatabase"
	@Test
	public void testSearch01() {
		try {
			this.fc.searchFantasy("1");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - FantasyControl SEARCH: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}
}