package br.com.chameleonfantasies.model.database;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.chameleonfantasies.model.entities.Cloth;
import br.com.chameleonfantasies.model.entities.Fantasy;

public class FantasySQLTest {
	private FantasySQL fsql;

	@Before
	public void setUp() {
		this.fsql = new FantasySQL();
		this.fsql.setUser("cf").setPass("1234").setPathToConnect("chameleonfantasies");
	}

	@After
	public void tearDown() throws SQLException {
		this.fsql.removeAll();
	}

	@Test
	public void testFantasySQL01() throws SQLException {
		Cloth hat = new Cloth("a", "a", false, false);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, true);
		Cloth arms = new Cloth("d", "d", true, false);
		Cloth shoes = new Cloth("e", "e", false, false);
		Fantasy f1 = new Fantasy("a", hat, top, bottom, arms, shoes, 10, 1000, 2000);
		Fantasy f2 = new Fantasy("a", hat, null, bottom, null, shoes, 20, 200, 500);

		Assert.assertEquals(0, this.fsql.list().size());
		this.fsql.insert(f1);
		Assert.assertEquals(f1.toString(), this.fsql.searchById(f1.getId()).toString());

		f1.setSellPrice(3000);
		this.fsql.update(f1);
		Assert.assertEquals(3000, fsql.searchById(f1.getId()).getSellPrice(), 0);
		Assert.assertNotEquals(0, this.fsql.list().size());

		this.fsql.insert(f2);
		Assert.assertEquals(2, this.fsql.searchByName(f2.getName()).size());

		this.fsql.remove(f1.getId());
		this.fsql.remove(f2.getId());
		Assert.assertEquals(0, this.fsql.list().size());
	}

	@Test
	public void testFantasySQL02() throws SQLException {
		Cloth hat = new Cloth("a", "a", false, false);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, true);
		Cloth arms = new Cloth("d", "d", true, false);
		Cloth shoes = new Cloth("e", "e", false, false);
		Fantasy f1 = new Fantasy("a", hat, top, bottom, arms, shoes, 10, 1000, 2000);
		Fantasy f2 = new Fantasy("b", hat, null, bottom, null, shoes, 20, 200, 500);

		this.fsql.insert(f1);
		this.fsql.insert(f2);
		Assert.assertEquals(1, this.fsql.searchByName(f2.getName()).size());
		Assert.assertEquals(2, this.fsql.removeAll());
	}
}