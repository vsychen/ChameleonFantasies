package br.com.cf.constraints;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SalarioValidator implements ConstraintValidator<Salario, String> {

	@Override
	public boolean isValid(String salario, ConstraintValidatorContext cxt) {
		return (new BigDecimal(salario)).compareTo(new BigDecimal("1000")) > 0;
	}
}