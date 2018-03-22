package test.data.entities;

import org.junit.Assert;
import org.junit.Test;

import main.data.entities.Cloth;

public class ClothTest {
	// Expected Answers
	private String expected_1 = "Camisa Marrom. Sem enfeites. Sem estampas.";
	private String expected_2 = "Vestido Vermelho. Sem enfeites. Com estampas.";
	private String expected_3 = "Casaco Preto. Com enfeites. Sem estampas.";
	private String expected_4 = "Calca Azul. Com enfeites. Com estampas.";

	@Test
	public void testCloth01() {
		Cloth c = new Cloth("Camisa", "Marrom", false, false);
		Assert.assertEquals(this.expected_1, c.toString());
	}

	@Test
	public void testCloth02() {
		Cloth c = new Cloth("Vestido", "Vermelho", false, true);
		Assert.assertEquals(this.expected_2, c.toString());
	}

	@Test
	public void testCloth03() {
		Cloth c = new Cloth("Casaco", "Preto", true, false);
		Assert.assertEquals(this.expected_3, c.toString());
	}

	@Test
	public void testCloth04() {
		Cloth c = new Cloth("Calca", "Azul", true, true);
		Assert.assertEquals(this.expected_4, c.toString());
	}
}