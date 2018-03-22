package br.com.chameleonfantasies.model.entities;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ClothTest {
	@Parameters(name = "Testing Cloth.toString() - Test #{index}:")
	public static Collection<Object[]> data() {
		return Arrays.asList(
				new Object[][] { { "Camisa", "Marrom", false, false, "Camisa Marrom. Sem enfeites. Sem estampas." },
						{ "Vestido", "Vermelho", false, true, "Vestido Vermelho. Sem enfeites. Com estampas." },
						{ "Casaco", "Preto", true, false, "Casaco Preto. Com enfeites. Sem estampas." },
						{ "Calca", "Azul", true, true, "Calca Azul. Com enfeites. Com estampas." } });
	}

	private Cloth input;
	private String expected;

	public ClothTest(String type, String color, boolean ornament, boolean stamp, String expected) {
		this.input = new Cloth(type, color, ornament, stamp);
		this.expected = expected;
	}

	@Test
	public void testClothToString() {
		Assert.assertEquals(this.expected, this.input.toString());
	}
}