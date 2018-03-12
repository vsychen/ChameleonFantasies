package br.com.cf.repository;

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
import br.com.cf.domain.Cliente;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class ClienteDAOUnitTest {
	@Autowired
	private ClienteDAO cdao;
	private Cliente cliente;

	@Before
	public void setUp() {
		cliente = new Cliente("João da Silva", "111.111.111-11", "124");
	}

	@After
	@Rollback
	public void tearDown() {
	}

	@Test
	@Transactional
	public void testSalvarProcurarCliente() {
		Assume.assumeTrue(cdao.procurar("111.111.111-11").getNome().equals(""));
		cdao.salvar(cliente);
		Assert.assertEquals("João da Silva", cdao.procurar("111.111.111-11").getNome());
	}

	@Test
	@Transactional
	public void testAtualizarCliente() {
		Assume.assumeTrue(cdao.procurar("111.111.111-11").getNome().equals(""));
		cdao.salvar(cliente);
		cliente.setNome("Maiara Ronessa");
		cdao.atualizar(cliente);
		Assert.assertEquals("Maiara Ronessa", cdao.procurar("111.111.111-11").getNome());
	}

	@Test
	@Transactional
	public void testSalvarOuAtualizarCliente() {
		Assume.assumeTrue(cdao.procurar("111.111.111-11").getNome().equals(""));
		cdao.salvarOuAtualizar(cliente);
		Assert.assertEquals("João da Silva", cdao.procurar("111.111.111-11").getNome());
		cliente.setGastos("1111");
		cdao.salvarOuAtualizar(cliente);
		Assert.assertEquals("1111", cdao.procurar("111.111.111-11").getGastos());
	}

	@Test
	@Transactional
	public void testListar() {
		int s = cdao.listar().size();
		cdao.salvar(cliente);
		cdao.salvar(new Cliente("teste02", "222.222.222-22", "21562"));
		cdao.salvar(new Cliente("teste03", "333.333.333-33", "0"));
		Assert.assertEquals(3 + s, cdao.listar().size());
	}

	@Test
	@Transactional
	public void testDeletarCliente() {
		int s = cdao.listar().size();
		cdao.salvar(cliente);
		cdao.deletar(cliente);
		Assert.assertEquals(s, cdao.listar().size());
	}
}