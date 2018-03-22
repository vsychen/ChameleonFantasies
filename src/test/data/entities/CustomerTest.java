package test.data.entities;

import org.junit.Assert;
import org.junit.Test;

import main.data.entities.Customer;

public class CustomerTest {
	// Expected Answers
	private String expected_1 = "Nome: Maria Auxiliadora\nGastos: 0.0\n";
	private String expected_2 = "123.456.789-00";

	@Test
	public void testCustomer01() {
		Customer c = new Customer("Maria Auxiliadora", "123.456.789-00");
		Assert.assertEquals(this.expected_1, c.toString());
	}

	@Test
	public void testCustomer02() {
		Customer c = new Customer("Maria Auxiliadora", "123.456.789-00");
		Assert.assertEquals(this.expected_2, c.getCpf());
	}
}