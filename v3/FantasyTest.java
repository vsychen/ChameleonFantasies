package br.com.chameleonfantasies.model.entities;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FantasyTest {
	@Parameters(name = "Testing Fantasy.toString() - Test #{index}:")
	public static Collection<Object[]> data() {
		Cloth hat = new Cloth("a", "a", false, false);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);
		return Arrays.asList(new Object[][] {
				{ "Fantasia", hat, top, bottom, arms, shoes, 10, 100, 500,
						"Nome: Fantasia\nChapéu: a a. Sem enfeites. Sem estampas.\nParte de cima: b b. Sem enfeites. Sem estampas.\nParte de baixo: c c. Sem enfeites. Sem estampas.\nBraços: d d. Sem enfeites. Sem estampas.\nSapatos: e e. Sem enfeites. Sem estampas.\nQuantidade: 10\nPreço de compra da fantasia: 100.0\nPreço de venda da fantasia: 500.0\n" },
				{ "Fant", null, null, bottom, null, null, 100, 1000, 5000,
						"Nome: Fant\nSem chapéu.\nSem parte de cima.\nParte de baixo: c c. Sem enfeites. Sem estampas.\nSem braços.\nSem sapatos.\nQuantidade: 100\nPreço de compra da fantasia: 1000.0\nPreço de venda da fantasia: 5000.0\n" },
				{ "F", null, null, null, null, null, 0, 0, 0,
						"Nome: F\nSem chapéu.\nSem parte de cima.\nSem parte de baixo.\nSem braços.\nSem sapatos.\nQuantidade: 0\nPreço de compra da fantasia: 0.0\nPreço de venda da fantasia: 0.0\n" } });
	}

	private Fantasy input;
	private String expected;

	public FantasyTest(String name, Cloth hat, Cloth top, Cloth bottom, Cloth arms, Cloth shoes, int quantity,
			double buyPrice, double sellPrice, String expected) {
		this.input = new Fantasy(name, hat, top, bottom, arms, shoes, quantity, buyPrice, sellPrice);
		this.expected = expected;
	}

	@Test
	public void testFantasyToString() {
		Assert.assertEquals(this.expected, this.input.toString());
	}
}