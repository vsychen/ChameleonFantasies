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
import br.com.cf.domain.Fantasia;
import br.com.cf.domain.Roupa;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class FantasiaDAOUnitTest {
	@Autowired
	private FantasiaDAO fdao;
	private Fantasia fantasia;

	@Before
	public void setUp() {
		fantasia = new Fantasia("Hulk", "HU0001", 10, "100", "200");
		fantasia.setPartes(null, null, new Roupa(fantasia, 'b', "Calça roxa rasgada"), null, null);
	}

	@After
	@Rollback
	public void tearDown() {
	}

	@Test
	@Transactional
	public void testSalvarProcurarFantasia() {
		Assume.assumeTrue(fdao.procurar("HU0001").getNome().equals(""));
		fdao.salvar(fantasia);
		Assert.assertEquals("Hulk", fdao.procurar("HU0001").getNome());
		Assert.assertEquals(5, fdao.procurar("HU0001").getPartes().size());
	}

	@Test
	@Transactional
	public void testAtualizarFantasia() {
		Assume.assumeTrue(fdao.procurar("HU0001").getNome().equals(""));
		fdao.salvar(fantasia);
		fantasia.setNome("Testando 1 2 3");
		fdao.atualizar(fantasia);
		Assert.assertEquals("Testando 1 2 3", fdao.procurar("HU0001").getNome());
	}

	@Test
	@Transactional
	public void testSalvarOuAtualizarFantasia() {
		Assume.assumeTrue(fdao.procurar("HU0001").getNome().equals(""));
		fdao.salvarOuAtualizar(fantasia);
		Assert.assertEquals("Hulk", fdao.procurar("HU0001").getNome());
		fantasia.setQuantidade(30);
		fantasia.setPrecoCompra("200");
		fantasia.setPrecoVenda("400");
		fdao.salvarOuAtualizar(fantasia);
		Assert.assertEquals(30, fdao.procurar("HU0001").getQuantidade());
		Assert.assertEquals("200", fdao.procurar("HU0001").getPrecoCompra());
		Assert.assertEquals("400", fdao.procurar("HU0001").getPrecoVenda());
	}

	@Test
	@Transactional
	public void testListar() {
		int s = fdao.listar().size();
		fdao.salvar(fantasia);
		fdao.salvar(new Fantasia("teste02", "TT0000", 15, "151", "21562"));
		fdao.salvar(new Fantasia("teste03", "TT0001", 41, "1325", "16838"));
		Assert.assertEquals(3 + s, fdao.listar().size());
	}

	@Test
	@Transactional
	public void testDeletarFantasia() {
		int s = fdao.listar().size();
		fdao.salvar(fantasia);
		fdao.deletar(fantasia);
		Assert.assertEquals(s, fdao.listar().size());
	}
}