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
 * Tests for the EDIT method in FantasyDatabase.
 * 
 * @author Victor Chen (vsyc)
 *
 */
public class FantasyDatabaseEditTest {
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
	}

	@After
	public void tearDown() throws DatabaseErrorException {
		if (!this.fd.isEmpty())
			this.fd.removeAll();

		this.fd = null;
	}

	// Test "EDIT one element of database with one element"
	@Test
	public void testEdit01() throws DatabaseErrorException {
		Fantasy f = this.fd.search("1");
		f.setHat(null);
		this.fd.edit("1", f);

		Assert.assertEquals(f.toString(), this.fd.search("1").toString());
	}

	// Test "EDIT one element of database with multiple elements"
	@Test
	public void testEdit02() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		Fantasy f_aux = new Fantasy("Fantasia", "2", hat, top, bottom, arms, shoes, 10, 100, 500);
		this.fd.add(f_aux);

		Fantasy f = this.fd.search("2");
		f.setShoes(null);
		this.fd.edit("2", f);

		Assert.assertEquals(f.toString(), this.fd.search("2").toString());
	}

	// Test "EDIT removed element"
	@Test
	public void testEdit03() throws DatabaseErrorException {
		Fantasy f = this.fd.search("1");
		this.fd.removeAll();
		f.setArms(null);

		try {
			this.fd.edit("1", f);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - FantasyDatabase EDIT: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}

	// Test "EDIT element inexistent in database"
	@Test
	public void testEdit04() throws DatabaseErrorException {
		Cloth hat = new Cloth("a", "a", false, true);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);

		Fantasy f = new Fantasy("Fantasia", "2", hat, top, bottom, arms, shoes, 10, 100, 500);
		f.setArms(null);

		try {
			this.fd.edit("2", f);
		} catch (DatabaseErrorException e) {
			System.out.println("DatabaseErrorException - FantasyDatabase EDIT: " + e.getMessage());
			Assert.assertEquals("Item inexistente na base de dados.", e.getMessage());
		}
	}
}