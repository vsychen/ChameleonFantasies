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
 * Tests for the ADD method in FantasyDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class FantasyDatabaseAddTest {
	private FantasyDatabase fd;

	@Before
	public void setUp() {
		this.fd = new FantasyDatabase();
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		if (!this.fd.isEmpty())
			this.fd.removeAll();

		this.fd = null;
	}

	// Test "no ADD"
	@Test
	public void testAdd01() throws DatabaseErrorException {
		this.fd.removeAll();

		Assert.assertEquals(true, this.fd.isEmpty());
	}

	// Test "ADD one element with all clothes to the database"
	@Test
	public void testAdd02() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		Fantasy f = new Fantasy("Fantasia", "1", hat, top, bottom, arms, shoes, 10, 100, 500);

		this.fd.removeAll();
		this.fd.add(f);

		Assert.assertEquals(false, this.fd.isEmpty());
	}

	// Test "ADD one element without hat to the database"
	@Test
	public void testAdd03() throws DatabaseErrorException {
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		Fantasy f = new Fantasy("Fantasia", "1", null, top, bottom, arms, shoes, 10, 100, 500);

		this.fd.removeAll();
		this.fd.add(f);

		Assert.assertEquals(false, this.fd.isEmpty());
	}

	// Test "ADD one element without top to the database"
	@Test
	public void testAdd04() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		Fantasy f = new Fantasy("Fantasia", "1", hat, null, bottom, arms, shoes, 10, 100, 500);

		this.fd.removeAll();
		this.fd.add(f);

		Assert.assertEquals(false, this.fd.isEmpty());
	}

	// Test "ADD one element without bottom to the database"
	@Test
	public void testAdd05() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		Fantasy f = new Fantasy("Fantasia", "1", hat, top, null, arms, shoes, 10, 100, 500);

		this.fd.removeAll();
		this.fd.add(f);

		Assert.assertEquals(false, this.fd.isEmpty());
	}

	// Test "ADD one element without arms to the database"
	@Test
	public void testAdd06() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		Fantasy f = new Fantasy("Fantasia", "1", hat, top, bottom, null, shoes, 10, 100, 500);

		this.fd.removeAll();
		this.fd.add(f);

		Assert.assertEquals(false, this.fd.isEmpty());
	}

	// Test "ADD one element without shoes to the database"
	@Test
	public void testAdd07() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);

		Fantasy f = new Fantasy("Fantasia", "1", hat, top, bottom, arms, null, 10, 100, 500);

		this.fd.removeAll();
		this.fd.add(f);

		Assert.assertEquals(false, this.fd.isEmpty());
	}

	// Test "ADD multiple elements to the database"
	@Test
	public void testAdd08() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		Fantasy f1 = new Fantasy("Fantasia", "1", hat, top, bottom, arms, shoes, 10, 100, 500);
		Fantasy f2 = new Fantasy("Fantasia", "3", hat, null, bottom, null, shoes, 10, 100, 500);
		Fantasy f3 = new Fantasy("Fantasia", "2", null, top, null, arms, shoes, 10, 100, 500);

		this.fd.add(f1);
		this.fd.add(f2);
		this.fd.add(f3);

		Assert.assertEquals(f1.toString(), this.fd.search("1").toString());
	}

	// Test "ADD duplicated element to the database"
	@Test
	public void testAdd09() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		Fantasy f = new Fantasy("Fantasia", "1", hat, top, bottom, arms, shoes, 10, 100, 500);
		this.fd.add(f);

		try {
			this.fd.add(f);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - FantasyDatabase ADD: " + e.getMessage());
			Assert.assertEquals("Item já existe na base de dados.", e.getMessage());
		}
	}
}