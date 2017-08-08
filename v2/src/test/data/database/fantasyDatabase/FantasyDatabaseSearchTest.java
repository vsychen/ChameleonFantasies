package test.data.database.fantasyDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.data.database.FantasyDatabase;
import main.data.entities.Cloth;
import main.data.entities.Fantasy;
import main.data.exceptions.DatabaseErrorException;

/***
 * Tests for the SEARCH method in FantasyDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class FantasyDatabaseSearchTest {
	private FantasyDatabase fd;

	@Before
	public void setUp() throws DatabaseErrorException {
		this.fd = new FantasyDatabase();
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		if (!this.fd.isEmpty())
			this.fd.removeAll();

		this.fd = null;
	}

	// Test "SEARCH database with one element"
	@Test
	public void testSearch01() throws DatabaseErrorException {
		Cloth hat = new Cloth("Máscara", "Preta", false, false);
		Cloth top = new Cloth("Suéter", "Cinza", false, true);
		Cloth bottom = new Cloth("Calça", "Cinza", false, false);
		Cloth arms = new Cloth("Luva", "Preta", false, false);
		Cloth shoes = new Cloth("Bota", "Preta", false, false);

		Fantasy f = new Fantasy("Batman", "1", hat, top, bottom, arms, shoes, 10, 500, 1000);
		this.fd.add(f);

		Assert.assertEquals(f.toString(), this.fd.search("1").toString());
	}

	// Test "SEARCH database with multiple elements"
	@Test
	public void testSearch02() throws DatabaseErrorException {
		Cloth hat = new Cloth("Máscara", "Preta", false, false);
		Cloth top = new Cloth("Suéter", "Cinza", false, true);
		Cloth bottom = new Cloth("Calça", "Cinza", false, false);
		Cloth arms = new Cloth("Luva", "Preta", false, false);
		Cloth shoes = new Cloth("Bota", "Preta", false, false);

		Fantasy f1 = new Fantasy("Batman", "1", hat, top, bottom, arms, shoes, 10, 500, 1000);
		Fantasy f2 = new Fantasy("Trash1", "3", hat, top, bottom, arms, shoes, 10, 500, 1000);
		Fantasy f3 = new Fantasy("Trash2", "2", hat, top, bottom, arms, shoes, 10, 500, 1000);

		this.fd.add(f1);
		this.fd.add(f2);
		this.fd.add(f3);

		Assert.assertEquals(f1.toString(), this.fd.search("1").toString());
	}

	// Test "SEARCH database with multiple elements"
	@Test
	public void testSearch03() throws DatabaseErrorException {
		Cloth hat = new Cloth("Máscara", "Preta", false, false);
		Cloth top = new Cloth("Suéter", "Cinza", false, true);
		Cloth bottom = new Cloth("Calça", "Cinza", false, false);
		Cloth arms = new Cloth("Luva", "Preta", false, false);
		Cloth shoes = new Cloth("Bota", "Preta", false, false);

		Fantasy f1 = new Fantasy("Batman", "1", hat, top, bottom, arms, shoes, 10, 500, 1000);
		Fantasy f2 = new Fantasy("Trash1", "3", hat, top, bottom, arms, shoes, 10, 500, 1000);
		Fantasy f3 = new Fantasy("Trash2", "2", hat, top, bottom, arms, shoes, 10, 500, 1000);

		this.fd.add(f1);
		this.fd.add(f2);
		this.fd.add(f3);

		Assert.assertEquals(f3.toString(), this.fd.search("2").toString());
	}

	// Test "SEARCH database with multiple elements"
	@Test
	public void testSearch04() throws DatabaseErrorException {
		Cloth hat = new Cloth("Máscara", "Preta", false, false);
		Cloth top = new Cloth("Suéter", "Cinza", false, true);
		Cloth bottom = new Cloth("Calça", "Cinza", false, false);
		Cloth arms = new Cloth("Luva", "Preta", false, false);
		Cloth shoes = new Cloth("Bota", "Preta", false, false);

		Fantasy f1 = new Fantasy("Batman", "1", hat, top, bottom, arms, shoes, 10, 500, 1000);
		Fantasy f2 = new Fantasy("Trash1", "3", hat, top, bottom, arms, shoes, 10, 500, 1000);
		Fantasy f3 = new Fantasy("Trash2", "2", hat, top, bottom, arms, shoes, 10, 500, 1000);

		this.fd.add(f1);
		this.fd.add(f2);
		this.fd.add(f3);

		Assert.assertEquals(f2.toString(), this.fd.search("3").toString());
	}

	// Test "SEARCH inexistent element in database"
	@Test
	public void testSearch05() {
		try {
			this.fd.search("2");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - FantasyDatabase SEARCH: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}
}