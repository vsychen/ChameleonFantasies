package br.com.cf.domain;

import org.junit.Assert;
import org.junit.Test;

public class FuncionarioSmokeTest {
	private Funcionario funcionario;

	@Test
	public void testConstructor() {
		funcionario = new Funcionario("testeNome", "testeCpf", "testeEmail", "testeTelefone", "testeCargo",
				"testeSalario");
		Assert.assertEquals("testeNome", funcionario.getNome());
		Assert.assertEquals("testeCpf", funcionario.getCpf());
		Assert.assertEquals("testeEmail", funcionario.getEmail());
		Assert.assertEquals("testeTelefone", funcionario.getTelefone());
		Assert.assertEquals("testeCargo", funcionario.getCargo());
		Assert.assertEquals("testeSalario", funcionario.getSalario());
	}

	@Test
	public void testNomeGetterSetter() {
		funcionario = new Funcionario("testeNome", "testeCpf", "testeEmail", "testeTelefone", "testeCargo",
				"testeSalario");
		funcionario.setNome("Novo nome");
		Assert.assertEquals("Novo nome", funcionario.getNome());
	}

	@Test
	public void testEmailGetterSetter() {
		funcionario = new Funcionario("testeNome", "testeCpf", "testeEmail", "testeTelefone", "testeCargo",
				"testeSalario");
		funcionario.setEmail("teste@teste.com");
		Assert.assertEquals("teste@teste.com", funcionario.getEmail());
	}

	@Test
	public void testTelefoneGetterSetter() {
		funcionario = new Funcionario("testeNome", "testeCpf", "testeEmail", "testeTelefone", "testeCargo",
				"testeSalario");
		funcionario.setTelefone("(dd)nnnnn-nnnn");
		Assert.assertEquals("(dd)nnnnn-nnnn", funcionario.getTelefone());
	}

	@Test
	public void testCargoGetterSetter() {
		funcionario = new Funcionario("testeNome", "testeCpf", "testeEmail", "testeTelefone", "testeCargo",
				"testeSalario");
		funcionario.setCargo("admin");
		Assert.assertEquals("admin", funcionario.getCargo());
	}

	@Test
	public void testSalarioGetterSetter() {
		funcionario = new Funcionario("testeNome", "testeCpf", "testeEmail", "testeTelefone", "testeCargo",
				"testeSalario");
		funcionario.setSalario("3000");
		Assert.assertEquals("3000", funcionario.getSalario());
	}
}