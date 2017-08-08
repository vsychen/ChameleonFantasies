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
 * Tests for the REMOVE method in FantasyDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class FantasyDatabaseRemoveTest {
	private FantasyDatabase fd;

	@Before
	public void setUp() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		this.fd = new FantasyDatabase();
		this.fd.add(new Fantasy("Fantasia", "1", hat, top, bottom, arms, shoes, 10, 100, 500));
		this.fd.add(new Fantasy("Fantasia", "2", null, top, bottom, arms, null, 10, 100, 500));
		this.fd.add(new Fantasy("Fantasia", "3", hat, null, bottom, null, shoes, 10, 100, 500));
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		if (!this.fd.isEmpty())
			this.fd.removeAll();

		this.fd = null;
	}

	// Test "REMOVE from database with multiple elements"
	@Test
	public void testRemove01() throws DatabaseErrorException {
		this.fd.remove("1");

		Fantasy c2 = this.fd.search("2");
		Fantasy c3 = this.fd.search("3");

		Assert.assertEquals(c2.toString() + "\n" + c3.toString() + "\n", this.fd.list());
	}

	// Test "REMOVE multiple elements from database with multiple elements"
	@Test
	public void testRemove02() throws DatabaseErrorException {
		this.fd.remove("1");
		this.fd.remove("2");

		Fantasy c = this.fd.search("3");

		Assert.assertEquals(c.toString() + "\n", this.fd.list());
	}

	// Test "REMOVE ALL elements from database with multiple elements"
	@Test
	public void testRemove03() throws DatabaseErrorException {
		this.fd.removeAll();

		Assert.assertEquals(true, this.fd.isEmpty());
	}

	// Test "REMOVE element from empty database"
	@Test
	public void testRemove04() throws DatabaseErrorException {
		this.fd.removeAll();

		try {
			this.fd.remove("1");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - FantasyDatabase REMOVE: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}

	// Test "REMOVE element inexistent in database"
	@Test
	public void testRemove05() throws DatabaseErrorException {
		this.fd.remove("1");

		try {
			this.fd.remove("1");
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - FantasyDatabase REMOVE: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}
}