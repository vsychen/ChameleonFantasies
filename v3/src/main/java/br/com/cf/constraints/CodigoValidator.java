package br.com.cf.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.RegexValidator;

public class CodigoValidator implements ConstraintValidator<Codigo, String> {

	@Override
	public boolean isValid(String codigo, ConstraintValidatorContext cxt) {
		return (new RegexValidator("([a-zA-Z]){2}([0-9]){4}")).isValid(codigo);
	}
}