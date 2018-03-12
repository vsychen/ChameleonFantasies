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
 * Tests for the LIST method in FantasyDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class FantasyDatabaseListTest {
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

	// Test "LIST empty database"
	@Test
	public void testList01() throws DatabaseErrorException {
		Assert.assertEquals("", this.fd.list());
	}

	// Test "LIST database with one element"
	@Test
	public void testList02() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		Fantasy f = new Fantasy("Fantasia", "1", hat, top, bottom, arms, shoes, 10, 100, 500);
		this.fd.add(f);

		Assert.assertEquals(f.toString() + "\n", this.fd.list());
	}

	// Test "LIST database with multiple elements"
	@Test
	public void testList03() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		Fantasy f1 = new Fantasy("Fantasia", "1", hat, top, bottom, arms, shoes, 10, 100, 500);
		Fantasy f2 = new Fantasy("Fantasia", "3", null, top, bottom, arms, null, 10, 100, 500);
		Fantasy f3 = new Fantasy("Fantasia", "2", hat, null, bottom, null, shoes, 10, 100, 500);

		this.fd.add(f1);
		this.fd.add(f2);
		this.fd.add(f3);

		Assert.assertEquals(f1.toString() + "\n" + f3.toString() + "\n" + f2.toString() + "\n", this.fd.list());
	}

	// Test "LIST database with multiple elements"
	@Test
	public void testList04() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		Fantasy f1 = new Fantasy("Fantasia", "1", hat, top, bottom, arms, shoes, 10, 100, 500);
		Fantasy f2 = new Fantasy("Fantasia", "2", null, top, bottom, arms, null, 10, 100, 500);
		Fantasy f3 = new Fantasy("Fantasia", "3", hat, null, bottom, null, shoes, 10, 100, 500);

		this.fd.add(f1);
		this.fd.add(f2);
		this.fd.add(f3);

		Assert.assertEquals(f1.toString() + "\n" + f2.toString() + "\n" + f3.toString() + "\n", this.fd.list());
	}
}