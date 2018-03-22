package br.com.chameleonfantasies.model.entities;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class EmployeeTest {
	@Parameters(name = "Testing Employee.toString() - Test #{index}:")
	public static Collection<Object[]> data() {
		Address a = new Address("Rua", "Cidade", "Estado", "Pais");
		return Arrays.asList(new Object[][] {
				{ "Cebolinha", "987.654.321-00", "cebola@cebolinha.com", a, "admin", 10000,
						"Nome: Cebolinha\nE-mail: cebola@cebolinha.com\nEndereço: Rua, Cidade - Estado, Pais\nCargo: admin\nSalário: 10000.0\n" },
				{ "Cebola", "987", "c@c.com", a, "stock", 10,
						"Nome: Cebola\nE-mail: c@c.com\nEndereço: Rua, Cidade - Estado, Pais\nCargo: stock\nSalário: 10.0\n" },
				{ "", "", "", new Address("", "", "", ""), "cashier", 0,
						"Nome: \nE-mail: \nEndereço: ,  - , \nCargo: cashier\nSalário: 0.0\n" },
				{ "", "", "", null, "", 0, "Nome: \nE-mail: \nEndereço: Sem endereço.\nCargo: \nSalário: 0.0\n" } });
	}

	private Employee input;
	private String expected;

	public EmployeeTest(String name, String cpf, String email, Address a, String job, double salary, String expected) {
		this.input = new Employee(name, cpf, email, a, job, salary);
		this.expected = expected;
	}

	@Test
	public void testEmployeeToString() {
		Assert.assertEquals(this.expected, this.input.toString());
	}
}