package test.data.entities;

import org.junit.Assert;
import org.junit.Test;

import main.data.entities.Address;

public class AddressTest {
	// Expected Answers
	private String expected = "Rua das rosas, Flores - Plantas, Seres Vivos";

	@Test
	public void testAddress01() {
		Address a = new Address("Rua das rosas", "Flores", "Plantas", "Seres Vivos");
		Assert.assertEquals(this.expected, a.toString());
	}
}