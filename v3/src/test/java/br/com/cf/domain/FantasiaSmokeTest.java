package br.com.cf.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FantasiaSmokeTest {
	private Fantasia fantasia;

	@Test
	public void testConstructor() {
		fantasia = new Fantasia("testeNome", "testeCodigo", 0, "testePrecoCompra", "testePrecoVenda");
		Assert.assertEquals("testeNome", fantasia.getNome());
		Assert.assertEquals("testeCodigo", fantasia.getCodigo());
		Assert.assertEquals(0, fantasia.getQuantidade());
		Assert.assertEquals("testePrecoCompra", fantasia.getPrecoCompra());
		Assert.assertEquals("testePrecoVenda", fantasia.getPrecoVenda());
	}

	@Test
	public void testNomeGetterSetter() {
		fantasia = new Fantasia("testeNome", "testeCodigo", 0, "testePrecoCompra", "testePrecoVenda");
		fantasia.setNome("Novo nome");
		Assert.assertEquals("Novo nome", fantasia.getNome());
	}

	@Test
	public void testQuantidadeGetterSetter() {
		fantasia = new Fantasia("testeNome", "testeCodigo", 0, "testePrecoCompra", "testePrecoVenda");
		fantasia.setQuantidade(10);
		Assert.assertEquals(10, fantasia.getQuantidade());
	}

	@Test
	public void testPrecoCompraGetterSetter() {
		fantasia = new Fantasia("testeNome", "testeCodigo", 0, "testePrecoCompra", "testePrecoVenda");
		fantasia.setPrecoCompra("1000");
		Assert.assertEquals("1000", fantasia.getPrecoCompra());
	}

	@Test
	public void testPrecoVendaGetterSetter() {
		fantasia = new Fantasia("testeNome", "testeCodigo", 0, "testePrecoCompra", "testePrecoVenda");
		fantasia.setPrecoVenda("2000");
		Assert.assertEquals("2000", fantasia.getPrecoVenda());
	}

	@Test
	public void testSalarioGetterSetter() {
		fantasia = new Fantasia("testeNome", "testeCodigo", 0, "testePrecoCompra", "testePrecoVenda");
		List<Roupa> partes = new ArrayList<Roupa>();
		partes.add(new Roupa(fantasia, 'h', "testeDescricaoChapeu"));
		partes.add(new Roupa(fantasia, 't', "testeDescricaoParteCima"));
		partes.add(new Roupa(fantasia, 'b', "testeDescricaoParteBaixo"));
		partes.add(new Roupa(fantasia, 'a', "testeDescricaoBracos"));
		partes.add(new Roupa(fantasia, 's', "testeDescricaoSapatos"));
		fantasia.setPartes(partes);
		Assert.assertEquals("htesteDescricaoChapeu",
				fantasia.getPartes().get(0).getTipo() + fantasia.getPartes().get(0).getDescricao());
		Assert.assertEquals("ttesteDescricaoParteCima",
				fantasia.getPartes().get(1).getTipo() + fantasia.getPartes().get(1).getDescricao());
		Assert.assertEquals("btesteDescricaoParteBaixo",
				fantasia.getPartes().get(2).getTipo() + fantasia.getPartes().get(2).getDescricao());
		Assert.assertEquals("atesteDescricaoBracos",
				fantasia.getPartes().get(3).getTipo() + fantasia.getPartes().get(3).getDescricao());
		Assert.assertEquals("stesteDescricaoSapatos",
				fantasia.getPartes().get(4).getTipo() + fantasia.getPartes().get(4).getDescricao());
	}
}