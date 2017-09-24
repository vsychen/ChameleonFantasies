package br.com.cf.service;

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
import br.com.cf.service.ClienteService;
import br.com.cf.exceptions.DataFieldException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class ClienteServiceUnitTest {
	@Autowired
	private ClienteService service;
	private ClientePOJO pojo;

	@Before
	public void setUp() {
		pojo = new ClientePOJO();
		pojo.setNome("João da Silva");
		pojo.setCpf("866.492.888-61");
		pojo.setGastos("124");
	}

	@After
	@Rollback
	public void tearDown() {
	}

	@Test
	@Transactional
	public void testSalvarProcurarCliente() {
		Assume.assumeTrue(service.procurar("866.492.888-61").getNome().equals(""));
		service.salvar(pojo);
		Assert.assertEquals("João da Silva", service.procurar("866.492.888-61").getNome());
	}

	@Test
	@Transactional
	public void testAtualizarCliente() {
		Assume.assumeTrue(service.procurar("866.492.888-61").getNome().equals(""));
		service.salvar(pojo);
		pojo.setNome("Maiara Ronessa");
		service.atualizar(pojo);
		Assert.assertEquals("Maiara Ronessa", service.procurar("866.492.888-61").getNome());
	}

	@Test
	@Transactional
	public void testSalvarOuAtualizarCliente() {
		Assume.assumeTrue(service.procurar("866.492.888-61").getNome().equals(""));
		service.salvarOuAtualizar(pojo);
		Assert.assertEquals("João da Silva", service.procurar("866.492.888-61").getNome());
		pojo.setGastos("1111");
		service.salvarOuAtualizar(pojo);
		Assert.assertEquals("1111", service.procurar("866.492.888-61").getGastos());
	}

	@Test
	@Transactional
	public void testListar() {
		int s = service.listar().size();
		service.salvar(pojo);
		pojo.setCpf("263.656.180-34");
		service.salvar(pojo);
		pojo.setCpf("681.266.821-40");
		service.salvar(pojo);
		Assert.assertEquals(3 + s, service.listar().size());
	}

	@Test
	@Transactional
	public void testRemoverCliente() {
		int s = service.listar().size();
		service.salvar(pojo);
		service.remover(pojo.getCpf());
		Assert.assertEquals(s, service.listar().size());
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testNomeInvalido() {
		pojo.setNome("a");
		service.salvar(pojo);
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testCpfInvalido01() {
		pojo.setCpf("1");
		service.salvar(pojo);
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testCpfInvalido02() {
		pojo.setCpf("a");
		service.salvar(pojo);
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testCpfInvalido03() {
		pojo.setCpf("111.111.111-11");
		service.salvar(pojo);
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testGastosInvalido01() {
		pojo.setGastos("-1");
		service.salvar(pojo);
	}

	@Test(expected = NumberFormatException.class)
	@Transactional
	public void testGastosInvalido02() {
		pojo.setGastos("a");
		service.salvar(pojo);
	}
}