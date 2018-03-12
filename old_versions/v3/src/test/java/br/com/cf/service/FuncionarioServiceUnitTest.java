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
import br.com.cf.domain.pojos.FuncionarioPOJO;
import br.com.cf.exceptions.DataFieldException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class FuncionarioServiceUnitTest {
	@Autowired
	private FuncionarioService service;
	private FuncionarioPOJO pojo;

	@Before
	public void setUp() {
		pojo = new FuncionarioPOJO();
		pojo.setNome("João da Silva");
		pojo.setCpf("866.492.888-61");
		pojo.setEmail("js@email.com");
		pojo.setTelefone("(11)11111-1111");
		pojo.setCargo("admin");
		pojo.setSalario("1124");
	}

	@After
	@Rollback
	public void tearDown() {
	}

	@Test
	@Transactional
	public void testSalvarProcurarFuncionario() {
		Assume.assumeTrue(service.procurar("866.492.888-61").getNome().equals(""));
		service.salvar(pojo);
		Assert.assertEquals("João da Silva", service.procurar("866.492.888-61").getNome());
	}

	@Test
	@Transactional
	public void testAtualizarFuncionario() {
		Assume.assumeTrue(service.procurar("866.492.888-61").getNome().equals(""));
		service.salvar(pojo);
		pojo.setNome("Maiara Ronessa");
		service.atualizar(pojo);
		Assert.assertEquals("Maiara Ronessa", service.procurar("866.492.888-61").getNome());
	}

	@Test
	@Transactional
	public void testSalvarOuAtualizarFuncionario() {
		Assume.assumeTrue(service.procurar("866.492.888-61").getNome().equals(""));
		service.salvarOuAtualizar(pojo);
		Assert.assertEquals("João da Silva", service.procurar("866.492.888-61").getNome());
		pojo.setSalario("1111");
		service.salvarOuAtualizar(pojo);
		Assert.assertEquals("1111", service.procurar("866.492.888-61").getSalario());
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
	public void testRemoverFuncionario() {
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
	public void testCpfInvalido() {
		pojo.setCpf("1");
		service.salvar(pojo);
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testEmailInvalido() {
		pojo.setEmail("e@e");
		service.salvar(pojo);
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testTelefoneInvalido() {
		pojo.setTelefone("1111-1111");
		service.salvar(pojo);
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testCargoInvalido() {
		pojo.setCargo("presidente");
		service.salvar(pojo);
	}

	@Test(expected = DataFieldException.class)
	@Transactional
	public void testSalarioInvalido01() {
		pojo.setSalario("-1");
		service.salvar(pojo);
	}

	@Test(expected = NumberFormatException.class)
	@Transactional
	public void testSalarioInvalido02() {
		pojo.setSalario("a");
		service.salvar(pojo);
	}
}