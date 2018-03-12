package test.data.entities;

import org.junit.Assert;
import org.junit.Test;

import main.data.entities.Address;
import main.data.entities.Employee;

public class EmployeeTest {
	// Expected Answers
	private String expected_1 = "Nome: Cebolinha\nE-mail: cebola@alho.com\nEndereço: Rua, Cidade - Estado, Pais\nCargo: admin\nSalário: 10000.0\n";
	private String expected_2 = "987.654.321-00";

	@Test
	public void testEmployee01() {
		Address a = new Address("Rua", "Cidade", "Estado", "Pais");
		Employee e = new Employee("Cebolinha", "987.654.321-00", "cebola@alho.com", a, "admin", 10000);
		Assert.assertEquals(this.expected_1, e.toString());
	}

	@Test
	public void testEmployee02() {
		Address a = new Address("Rua", "Cidade", "Estado", "Pais");
		Employee e = new Employee("Cebolinha", "987.654.321-00", "cebola@alho.com", a, "admin", 10000);
		Assert.assertEquals(this.expected_2, e.getCpf());
	}
}