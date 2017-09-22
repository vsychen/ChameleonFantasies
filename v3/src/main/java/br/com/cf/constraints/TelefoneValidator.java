package br.com.cf.constraints;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.RegexValidator;

public class TelefoneValidator implements ConstraintValidator<Telefone, String> {
	private final String[] notDDDs = { "10", "20", "23", "25", "26", "29", "30", "36", "39", "40", "50", "52", "56",
			"57", "58", "59", "60", "70", "72", "76", "78", "80", "90" };

	@Override
	public boolean isValid(String telefone, ConstraintValidatorContext cxt) {
		if (telefone.length() != 14)
			return false;

		String ddd = telefone.substring(1, 3);
		return (new RegexValidator("\\(([0-9]{2})\\)([0-9]){5}-([0-9]){4}")).isValid(telefone)
				&& !(Arrays.asList(notDDDs).contains(ddd));
	}
}