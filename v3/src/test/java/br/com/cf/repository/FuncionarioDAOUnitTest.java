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
import br.com.cf.domain.Funcionario;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class FuncionarioDAOUnitTest {
	@Autowired
	private FuncionarioDAO fdao;
	private Funcionario funcionario;

	@Before
	public void setUp() {
		funcionario = new Funcionario("João da Silva", "111.111.111-11", "js@email.com", "(11)11111-1111", "admin",
				"124");
	}

	@After
	@Rollback
	public void tearDown() {
	}

	@Test
	@Transactional
	public void testSalvarProcurarFuncionario() {
		Assume.assumeTrue(fdao.procurar("111.111.111-11").getNome().equals(""));
		fdao.salvar(funcionario);
		Assert.assertEquals("João da Silva", fdao.procurar("111.111.111-11").getNome());
	}

	@Test
	@Transactional
	public void testAtualizarFuncionario() {
		Assume.assumeTrue(fdao.procurar("111.111.111-11").getNome().equals(""));
		fdao.salvar(funcionario);
		funcionario.setNome("Maiara Ronessa");
		fdao.atualizar(funcionario);
		Assert.assertEquals("Maiara Ronessa", fdao.procurar("111.111.111-11").getNome());
	}

	@Test
	@Transactional
	public void testSalvarOuAtualizarFuncionario() {
		Assume.assumeTrue(fdao.procurar("111.111.111-11").getNome().equals(""));
		fdao.salvarOuAtualizar(funcionario);
		Assert.assertEquals("João da Silva", fdao.procurar("111.111.111-11").getNome());
		funcionario.setSalario("1111");
		fdao.salvarOuAtualizar(funcionario);
		Assert.assertEquals("1111", fdao.procurar("111.111.111-11").getSalario());
	}

	@Test
	@Transactional
	public void testListar() {
		int s = fdao.listar().size();
		fdao.salvar(funcionario);
		fdao.salvar(
				new Funcionario("teste02", "222.222.222-22", "teste@email.com", "(22)22222-2222", "cashier", "21562"));
		fdao.salvar(
				new Funcionario("teste03", "333.333.333-33", "anothertest@email.com", "(33)33333-3333", "stock", "0"));
		Assert.assertEquals(3 + s, fdao.listar().size());
	}

	@Test
	@Transactional
	public void testDeletarFuncionario() {
		int s = fdao.listar().size();
		fdao.salvar(funcionario);
		fdao.deletar(funcionario);
		Assert.assertEquals(s, fdao.listar().size());
	}
}