package br.com.cf.fachada;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.cf.configuration.TestConfig;
import br.com.cf.domain.pojos.ClientePOJO;
import br.com.cf.domain.pojos.FantasiaPOJO;
import br.com.cf.domain.pojos.FuncionarioPOJO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class FachadaSmokeTest {
	@Autowired
	private Fachada fachada;
	private ClientePOJO cliente;
	private FantasiaPOJO fantasia;
	private FuncionarioPOJO funcionario;

	@Before
	public void setUp() {
		cliente = new ClientePOJO();
		cliente.setNome("João da Silva");
		cliente.setCpf("866.492.888-61");
		cliente.setGastos("124");

		fantasia = new FantasiaPOJO();
		fantasia.setNome("Hulk");
		fantasia.setCodigo("HU0001");
		fantasia.setQuantidade(10);
		fantasia.setPrecoCompra("124");
		fantasia.setPrecoVenda("248");
		fantasia.setDescParteBaixo("Calça roxa rasgada");

		funcionario = new FuncionarioPOJO();
		funcionario.setNome("João da Silva");
		funcionario.setCpf("866.492.888-61");
		funcionario.setEmail("js@email.com");
		funcionario.setTelefone("(11)11111-1111");
		funcionario.setCargo("admin");
		funcionario.setSalario("1124");
	}

	@After
	@Rollback
	public void tearDown() {
	}

	@Test
	@Transactional
	public void testSalvarCliente() {
		int s = fachada.listarClientes().size();
		fachada.salvarCliente(cliente);
		Assert.assertEquals(1 + s, fachada.listarClientes().size());
	}

	@Test
	@Transactional
	public void testProcurarCliente() {
		Assume.assumeTrue(fachada.procurarCliente(cliente.getCpf()).getNome().equals(""));
		fachada.salvarCliente(cliente);
		Assert.assertEquals(cliente.getNome(), fachada.procurarCliente(cliente.getCpf()).getNome());
	}

	@Test
	@Transactional
	public void testEditarCliente() {
		Assume.assumeTrue(fachada.procurarCliente(cliente.getCpf()).getNome().equals(""));
		fachada.salvarCliente(cliente);
		cliente.setNome("Mousse de chocolate");
		fachada.editarCliente(cliente);
		Assert.assertEquals(cliente.getNome(), fachada.procurarCliente(cliente.getCpf()).getNome());
	}

	@Test
	@Transactional
	public void testRemoverCliente() {
		Assume.assumeTrue(fachada.procurarCliente(cliente.getCpf()).getNome().equals(""));
		fachada.salvarCliente(cliente);
		fachada.removerCliente(cliente.getCpf());
		Assert.assertEquals("", fachada.procurarCliente(cliente.getCpf()).getNome());
	}

	@Test
	@Transactional
	public void testListarClientes() {
		int s = fachada.listarClientes().size();
		fachada.salvarCliente(cliente);
		cliente.setCpf("263.656.180-34");
		fachada.salvarCliente(cliente);
		cliente.setCpf("681.266.821-40");
		fachada.salvarCliente(cliente);
		Assert.assertEquals(3 + s, fachada.listarClientes().size());
	}

	@Test
	@Transactional
	public void testSalvarFantasia() {
		int s = fachada.listarFantasias().size();
		fachada.salvarFantasia(fantasia);
		Assert.assertEquals(1 + s, fachada.listarFantasias().size());
	}

	@Test
	@Transactional
	public void testProcurarFantasia() {
		Assume.assumeTrue(fachada.procurarFantasia(fantasia.getCodigo()).getNome().equals(""));
		fachada.salvarFantasia(fantasia);
		Assert.assertEquals(fantasia.getNome(), fachada.procurarFantasia(fantasia.getCodigo()).getNome());
	}

	@Test
	@Transactional
	public void testEditarFantasia() {
		Assume.assumeTrue(fachada.procurarFantasia(fantasia.getCodigo()).getNome().equals(""));
		fachada.salvarFantasia(fantasia);
		fantasia.setNome("Verdão fortão");
		fachada.editarFantasia(fantasia);
		Assert.assertEquals(fantasia.getNome(), fachada.procurarFantasia(fantasia.getCodigo()).getNome());
	}

	@Test
	@Transactional
	public void testRemoverFantasia() {
		Assume.assumeTrue(fachada.procurarFantasia(fantasia.getCodigo()).getNome().equals(""));
		fachada.salvarFantasia(fantasia);
		fachada.removerFantasia(fantasia.getCodigo());
		Assert.assertEquals("", fachada.procurarFantasia(fantasia.getCodigo()).getNome());
	}

	@Test
	@Transactional
	public void testListarFantasias() {
		int s = fachada.listarFantasias().size();
		fachada.salvarFantasia(fantasia);
		fantasia.setCodigo("HU0002");
		fachada.salvarFantasia(fantasia);
		fantasia.setCodigo("HU0003");
		fachada.salvarFantasia(fantasia);
		Assert.assertEquals(3 + s, fachada.listarFantasias().size());
	}

	@Test
	@Transactional
	public void testComprarFantasias() {
		fachada.salvarFantasia(fantasia);
		fachada.comprarFantasias(fantasia.getCodigo(), 10);
		Assert.assertEquals(fantasia.getQuantidade() + 10,
				fachada.procurarFantasia(fantasia.getCodigo()).getQuantidade());
	}

	@Test
	@Transactional
	public void testVenderFantasias01() {
		fachada.salvarFantasia(fantasia);
		fachada.venderFantasias(fantasia.getCodigo(), "", 5);
		Assert.assertEquals(fantasia.getQuantidade() - 5, fachada.procurarFantasia("HU0001").getQuantidade());
	}

	@Test
	@Transactional
	public void testVenderFantasias02() {
		fachada.salvarCliente(cliente);
		fachada.salvarFantasia(fantasia);
		fachada.venderFantasias(fantasia.getCodigo(), cliente.getCpf(), 5);
		Assert.assertEquals(fantasia.getQuantidade() - 5, fachada.procurarFantasia("HU0001").getQuantidade());
		Assert.assertEquals(new BigDecimal(fantasia.getPrecoVenda()).multiply(new BigDecimal("5")).stripTrailingZeros()
				.toPlainString(), fachada.procurarCliente("866.492.888-61").getGastos());
	}

	@Test
	@Transactional
	public void testSalvarFuncionario() {
		int s = fachada.listarFuncionarios().size();
		fachada.salvarFuncionario(funcionario);
		Assert.assertEquals(1 + s, fachada.listarFuncionarios().size());
	}

	@Test
	@Transactional
	public void testProcurarFuncionario() {
		Assume.assumeTrue(fachada.procurarFuncionario(funcionario.getCpf()).getNome().equals(""));
		fachada.salvarFuncionario(funcionario);
		Assert.assertEquals(funcionario.getNome(), fachada.procurarFuncionario(funcionario.getCpf()).getNome());
	}

	@Test
	@Transactional
	public void testEditarFuncionario() {
		Assume.assumeTrue(fachada.procurarFuncionario(funcionario.getCpf()).getNome().equals(""));
		fachada.salvarFuncionario(funcionario);
		cliente.setNome("Mousse de chocolate");
		fachada.editarFuncionario(funcionario);
		Assert.assertEquals(funcionario.getNome(), fachada.procurarFuncionario(funcionario.getCpf()).getNome());
	}

	@Test
	@Transactional
	public void testRemoverFuncionario() {
		Assume.assumeTrue(fachada.procurarFuncionario(funcionario.getCpf()).getNome().equals(""));
		fachada.salvarFuncionario(funcionario);
		fachada.removerFuncionario(funcionario.getCpf());
		Assert.assertEquals("", fachada.procurarFuncionario(funcionario.getCpf()).getNome());
	}

	@Test
	@Transactional
	public void testListarFuncionarios() {
		int s = fachada.listarFuncionarios().size();
		fachada.salvarFuncionario(funcionario);
		funcionario.setCpf("263.656.180-34");
		fachada.salvarFuncionario(funcionario);
		funcionario.setCpf("681.266.821-40");
		fachada.salvarFuncionario(funcionario);
		Assert.assertEquals(3 + s, fachada.listarFuncionarios().size());
	}
}