package test.data.entities;

import org.junit.Assert;
import org.junit.Test;

import main.data.entities.Cloth;
import main.data.entities.Fantasy;

public class FantasyTest {
	// Expected Answers
	private String expected_1 = "Nome: Fantasia\nChapeu: a a. Sem enfeites. Sem estampas.\nParte de cima: b b. Sem enfeites. Sem estampas.\nParte de baixo: c c. Sem enfeites. Sem estampas.\nBraços: d d. Sem enfeites. Sem estampas.\nSapatos: e e. Sem enfeites. Sem estampas.\nQuantidade: 10\nPreço de compra da fantasia: 100.0\nPreço de venda da fantasia: 500.0\n";
	private String expected_2 = "Nome: Fantasia\nParte de cima: b b. Sem enfeites. Sem estampas.\nParte de baixo: c c. Sem enfeites. Sem estampas.\nBraços: d d. Sem enfeites. Sem estampas.\nSapatos: e e. Sem enfeites. Sem estampas.\nQuantidade: 10\nPreço de compra da fantasia: 100.0\nPreço de venda da fantasia: 500.0\n";
	private String expected_3 = "Nome: Fantasia\nChapeu: a a. Sem enfeites. Sem estampas.\nParte de baixo: c c. Sem enfeites. Sem estampas.\nBraços: d d. Sem enfeites. Sem estampas.\nSapatos: e e. Sem enfeites. Sem estampas.\nQuantidade: 10\nPreço de compra da fantasia: 100.0\nPreço de venda da fantasia: 500.0\n";
	private String expected_4 = "Nome: Fantasia\nChapeu: a a. Sem enfeites. Sem estampas.\nParte de cima: b b. Sem enfeites. Sem estampas.\nBraços: d d. Sem enfeites. Sem estampas.\nSapatos: e e. Sem enfeites. Sem estampas.\nQuantidade: 10\nPreço de compra da fantasia: 100.0\nPreço de venda da fantasia: 500.0\n";
	private String expected_5 = "Nome: Fantasia\nChapeu: a a. Sem enfeites. Sem estampas.\nParte de cima: b b. Sem enfeites. Sem estampas.\nParte de baixo: c c. Sem enfeites. Sem estampas.\nSapatos: e e. Sem enfeites. Sem estampas.\nQuantidade: 10\nPreço de compra da fantasia: 100.0\nPreço de venda da fantasia: 500.0\n";
	private String expected_6 = "Nome: Fantasia\nChapeu: a a. Sem enfeites. Sem estampas.\nParte de cima: b b. Sem enfeites. Sem estampas.\nParte de baixo: c c. Sem enfeites. Sem estampas.\nBraços: d d. Sem enfeites. Sem estampas.\nQuantidade: 10\nPreço de compra da fantasia: 100.0\nPreço de venda da fantasia: 500.0\n";

	@Test
	public void testFantasy01() {
		Cloth hat = new Cloth("a", "a", false, false);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);
		Fantasy f = new Fantasy("Fantasia", "1", hat, top, bottom, arms, shoes, 10, 100, 500);

		Assert.assertEquals(this.expected_1, f.toString());
	}

	@Test
	public void testFantasy02() {
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);
		Fantasy f = new Fantasy("Fantasia", "1", null, top, bottom, arms, shoes, 10, 100, 500);

		Assert.assertEquals(this.expected_2, f.toString());
	}

	@Test
	public void testFantasy03() {
		Cloth hat = new Cloth("a", "a", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);
		Fantasy f = new Fantasy("Fantasia", "1", hat, null, bottom, arms, shoes, 10, 100, 500);

		Assert.assertEquals(this.expected_3, f.toString());
	}

	@Test
	public void testFantasy04() {
		Cloth hat = new Cloth("a", "a", false, false);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);
		Fantasy f = new Fantasy("Fantasia", "1", hat, top, null, arms, shoes, 10, 100, 500);

		Assert.assertEquals(this.expected_4, f.toString());
	}

	@Test
	public void testFantasy05() {
		Cloth hat = new Cloth("a", "a", false, false);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth shoes = new Cloth("e", "e", false, false);
		Fantasy f = new Fantasy("Fantasia", "1", hat, top, bottom, null, shoes, 10, 100, 500);

		Assert.assertEquals(this.expected_5, f.toString());
	}

	@Test
	public void testFantasy06() {
		Cloth hat = new Cloth("a", "a", false, false);
		Cloth top = new Cloth("b", "b", false, false);
		Cloth bottom = new Cloth("c", "c", false, false);
		Cloth arms = new Cloth("d", "d", false, false);
		Fantasy f = new Fantasy("Fantasia", "1", hat, top, bottom, arms, null, 10, 100, 500);

		Assert.assertEquals(this.expected_6, f.toString());
	}
}