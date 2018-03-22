package br.com.chameleonfantasies.model.entities;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CustomerTest {
	@Parameters(name = "Testing Customer.toString() - Test #{index}:")
	public static Collection<Object[]> data() {
		return Arrays.asList(
				new Object[][] { { "Maria Auxiliadora", "123.456.789-00", 0, "Nome: Maria Auxiliadora\nGastos: 0.0\n" },
						{ "Ana Lúcia", "123.456.789-00", 100, "Nome: Ana Lúcia\nGastos: 100.0\n" },
						{ "", "", 0, "Nome: \nGastos: 0.0\n" } });
	}

	private Customer input;
	private String expected;

	public CustomerTest(String name, String cpf, double spending, String expected) {
		this.input = new Customer(name, cpf, spending);
		this.expected = expected;
	}

	@Test
	public void testCustomerToString() {
		Assert.assertEquals(this.expected, this.input.toString());
	}
}