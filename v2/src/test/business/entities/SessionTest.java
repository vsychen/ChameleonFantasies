package test.business.entities;

import org.junit.Assert;
import org.junit.Test;

import main.business.entities.Session;

public class SessionTest {

	@Test
	public void testSession01() {
		Session s = new Session("111.111.111-11", "1234", "admin");
		Assert.assertEquals("Cpf: 111.111.111-11\nCargo: admin\nHora do login: " + s.getDateLogin() + "\n",
				s.toString());
	}
}