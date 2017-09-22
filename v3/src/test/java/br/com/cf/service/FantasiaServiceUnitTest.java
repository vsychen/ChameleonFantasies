package br.com.cf.service;

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
import br.com.cf.exceptions.DataFieldException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class FantasiaServiceUnitTest {
	@Autowired
	private ClienteService clientes;
	@Autowired
	private FantasiaService service;
	private FantasiaPOJO pojo;

	@Before
	public void setUp() {
		pojo = new FantasiaPOJO();
		pojo.setNome("Hulk");
		pojo.setCodigo("HU0001");
		pojo.setQuantidade(10);
		pojo.setPrecoCompra("124");
		pojo.setPrecoVenda("248");
		pojo.setDescParteBaixo("Calça roxa rasgada");
	}

	@After
	@Rollback
	public void tearDown() {
	}

	@Test
	@Transactional
	public void testSalvarProcurarFantasia() {
		Assume.assumeTrue(service.procurar("HU0001").getNome().equals(""));
		service.salvar(pojo);
		Assert.assertEquals("Hulk", service.procurar("HU0001").getNome());
	}

	@Test
	@Transactional
	public void testAtualizarFantasia() {
		Assume.assumeTrue(service.procurar("HU0001").getNome().equals(""));
		service.salvar(pojo);
		pojo.setNome("Fortão verde");
		service.atualizar(pojo);
		Assert.assertEquals("Fortão verde", service.procurar("HU0001").getNome());
	}

	@Test
	@Transactional
	public void testSalvarOuAtualizarFantasia() {
		Assume.assumeTrue(service.procurar("HU0001").getNome().equals(""));
		service.salvarOuAtualizar(pojo);
		Assert.assertEquals("Hulk", service.procurar("HU0001").getNome());
		pojo.setPrecoCompra("200");
		service.salvarOuAtualizar(pojo);
		Assert.assertEquals("200", service.procurar("HU0001").getPrecoCompra());
	}

	@Test
	@Transactional
	public void testListar() {
		int s = service.listar().size();
		service.salvar(pojo);
		pojo.setCodigo("HU0002");
		service.salvar(pojo);
		pojo.setCodigo("HU0003");
		service.salvar(pojo);
		Assert.assertEquals(3 + s, service.listar().size());
	}

	@Test
	@Transactional
	public void testRemoverFantasia() {
		int s = service.listar().size();
		service.salvar(pojo);
		service.remover(pojo.getCodigo());
		Assert.assertEquals(s, service.listar().size());
	}

	@Test
	@Transactional
	public void testComprarFantasias() {
		service.salvarOuAtualizar(pojo);
		service.comprarFantasias("HU0001", 10);
		Assert.assertEquals(pojo.getQuantidade() + 10, service.procurar("HU0001").getQuantidade());
	}

	@Test
	@Transactional
	public void testVenderFantasias01() {
		service.salvarOuAtualizar(pojo);
		service.venderFantasias("HU0001", 5);
		Assert.assertEquals(pojo.getQuantidade() - 5, service.procurar("HU0001").getQuantidade());
	}

	@Test
	@Transactional
	public void testVenderFantasias02() {
		Assume.assumeTrue(clientes.procurar("866.492.888-61").getNome().equals(""));
		ClientePOJO cliente = new ClientePOJO();
		cliente.setNome("Teste da Venda");
		cliente.setCpf("866.492.888-61");
		cliente.setGastos("0");

		clientes.salvar(cliente);
		service.salvarOuAtualizar(pojo);
		service.venderFantasias("HU0001", "866.492.888-61", 5);
		Assert.assertEquals(pojo.getQuantidade() - 5, service.procurar("HU0001").getQuantidade());
		Assert.assertEquals(
				new BigDecimal(pojo.getPrecoVenda()).multiply(new BigDecimal("5")).stripTrailingZeros().toPlainString(),
				clientes.procurar("866.492.888-61").getGastos());
	}

	@Test
	@Transactional
	public void testMudarPrecos() {
		service.salvarOuAtualizar(pojo);
		service.mudarPrecos("HU0001", "1000", "2000");
		Assert.assertEquals("1000", service.procurar("HU0001").getPrecoCompra());
		Assert.assertEquals("2000", service.procurar("HU0001").getPrecoVenda());
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testNomeInvalido() {
		pojo.setNome("a");
		service.salvar(pojo);
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testCodigoInvalido() {
		pojo.setCodigo("1");
		service.salvar(pojo);
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testPrecoCompraInvalido01() {
		pojo.setPrecoCompra("-1");
		service.salvar(pojo);
	}

	@Test(expected = NumberFormatException.class)
	@Transactional
	public void testPrecoCompraInvalido02() {
		pojo.setPrecoCompra("a");
		service.salvar(pojo);
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testPrecoVendaInvalido01() {
		pojo.setPrecoVenda("-1");
		service.salvar(pojo);
	}

	@Test(expected = NumberFormatException.class)
	@Transactional
	public void testPrecoVendaInvalido02() {
		pojo.setPrecoVenda("a");
		service.salvar(pojo);
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testPrecoInvalido() {
		pojo.setPrecoCompra("1111");
		pojo.setPrecoVenda("1110");
		service.salvar(pojo);
	}
}