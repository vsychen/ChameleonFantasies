package br.com.cf.domain;

import org.junit.Assert;
import org.junit.Test;

public class ClienteSmokeTest {
	private Cliente cliente;

	@Test
	public void testConstructor() {
		cliente = new Cliente("testeNome", "testeCpf", "testeSalario");
		Assert.assertEquals("testeNome", cliente.getNome());
		Assert.assertEquals("testeCpf", cliente.getCpf());
		Assert.assertEquals("testeSalario", cliente.getGastos());
	}

	@Test
	public void testNomeGetterSetter() {
		cliente = new Cliente("testeNome", "testeCpf", "testeSalario");
		cliente.setNome("Novo nome");
		Assert.assertEquals("Novo nome", cliente.getNome());
	}

	@Test
	public void testGastosGetterSetter() {
		cliente = new Cliente("testeNome", "testeCpf", "testeSalario");
		cliente.setGastos("215");
		Assert.assertEquals("215", cliente.getGastos());
	}
}