package br.com.chameleonfantasies.model.entities;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SessionTest {
	@Parameters(name = "Testing Session.toString() - Test #{index}:")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ "111.111.111-11", "1234", "admin", "Cpf: 111.111.111-11\nCargo: admin\nHora do login: " },
				{ "", "", "", "Cpf: \nCargo: \nHora do login: " } });
	}

	private Session input;
	private String expected;

	public SessionTest(String cpf, String password, String job, String expected) {
		this.input = new Session(cpf, password, job);
		this.expected = expected;
	}

	@Test
	public void testSessionToString() {
		Assert.assertEquals(true, this.input.toString().contains(this.expected));
	}
}