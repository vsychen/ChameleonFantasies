package br.com.chameleonfantasies.model.entities;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class AddressTest {
	@Parameters(name = "Testing Address.toString() - Test #{index}:")
	public static Collection<Object[]> data() {
		return Arrays.asList(
				new Object[][] { { "Rua A", "Cidade B", "Estado C", "País D", "Rua A, Cidade B - Estado C, País D" },
						{ "", "Cidade B", "Estado C", "País D", ", Cidade B - Estado C, País D" },
						{ "Rua A", "", "Estado C", "País D", "Rua A,  - Estado C, País D" },
						{ "Rua A", "Cidade B", "", "País D", "Rua A, Cidade B - , País D" },
						{ "Rua A", "Cidade B", "Estado C", "", "Rua A, Cidade B - Estado C, " } });
	}

	private Address input;
	private String expected;

	public AddressTest(String street, String city, String state, String country, String expected) {
		this.input = new Address(street, city, state, country);
		this.expected = expected;
	}

	@Test
	public void testAddressToString() {
		Assert.assertEquals(this.expected, this.input.toString());
	}
}